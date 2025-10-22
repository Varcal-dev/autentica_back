package com.varcal.ecommer.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.varcal.ecommer.Models.Carrito;
import com.varcal.ecommer.Models.CarritoDetalle;
import com.varcal.ecommer.Models.Cupon;
import com.varcal.ecommer.Models.DetallePedido;
import com.varcal.ecommer.Models.DireccionUsuario;
import com.varcal.ecommer.Models.EstadoPedido;
import com.varcal.ecommer.Models.Pedido;
import com.varcal.ecommer.Models.Usuario;
import com.varcal.ecommer.Repos.CarritoRepository;
import com.varcal.ecommer.Repos.CuponRepository;
import com.varcal.ecommer.Repos.DetallePedidoRepository;
import com.varcal.ecommer.Repos.PedidoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CarritoRepository carritoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final CuponService cuponService;

    public PedidoService(PedidoRepository pedidoRepository,
            CarritoRepository carritoRepository,
            DetallePedidoRepository detallePedidoRepository,
            CuponService cuponService) {
        this.pedidoRepository = pedidoRepository;
        this.carritoRepository = carritoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.cuponService = cuponService;
    }

    @Transactional
    public Pedido crearPedidoDesdeCarrito(Integer idCarrito, String metodoPago,
            DireccionUsuario direccion, String codigoCupon) {
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        if (carrito.getDetalles().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        // Calcular el total del carrito
        BigDecimal total = BigDecimal.ZERO;
        for (CarritoDetalle det : carrito.getDetalles()) {
            BigDecimal precioUnitario = det.getPrecioUnitario();

            if (precioUnitario == null || precioUnitario.compareTo(BigDecimal.ZERO) == 0) {
                // Usa el precio base del producto o variante
                if (det.getVariante() != null && det.getVariante().getPrecioAjuste() != null) {
                    precioUnitario = det.getVariante().getPrecioAjuste();
                } else {
                    precioUnitario = det.getProducto().getPrecio();
                }
                det.setPrecioUnitario(precioUnitario);
            }

            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(det.getCantidad()));
            total = total.add(subtotal);
        }

        BigDecimal totalConDescuento = total;
        Cupon cuponAplicado = null;

        // Si se envió un cupón, lo validamos y aplicamos
        if (codigoCupon != null && !codigoCupon.isEmpty()) {
            cuponAplicado = cuponService.validarCupon(codigoCupon, total);
            totalConDescuento = cuponService.aplicarDescuento(cuponAplicado, total);
            cuponService.incrementarUso(cuponAplicado);
        }

        // Crear pedido
        Pedido pedido = Pedido.builder()
                .usuario(carrito.getUsuario())
                .direccionEntrega(direccion)
                .estado(EstadoPedido.PENDIENTE)
                .metodoPago(metodoPago)
                .fechaPedido(LocalDateTime.now())
                .total(totalConDescuento.doubleValue())
                .cupon(cuponAplicado)
                .build();

        pedidoRepository.save(pedido);

        // Crear los detalles del pedido
        List<DetallePedido> detalles = carrito.getDetalles().stream().map(det -> {
            BigDecimal precioUnitario = det.getPrecioUnitario();
            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(det.getCantidad()));

            return DetallePedido.builder()
                    .pedido(pedido)
                    .producto(det.getProducto())
                    .variante(det.getVariante())
                    .cantidad(det.getCantidad())
                    .precioUnitario(precioUnitario.doubleValue())
                    .subtotal(subtotal.doubleValue())
                    .build();
        }).collect(Collectors.toList());

        detallePedidoRepository.saveAll(detalles);
        pedido.setDetalles(detalles);

        // Vaciar carrito
        carrito.getDetalles().clear();
        carritoRepository.save(carrito);

        return pedido;
    }

    public List<Pedido> obtenerPedidosPorUsuario(Usuario usuario) {
        return pedidoRepository.findByUsuarioOrderByFechaPedidoDesc(usuario);
    }
}
