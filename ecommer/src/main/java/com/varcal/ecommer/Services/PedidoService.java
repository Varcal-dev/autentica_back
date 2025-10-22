package com.varcal.ecommer.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.varcal.ecommer.Models.Carrito;
import com.varcal.ecommer.Models.CarritoDetalle;
import com.varcal.ecommer.Models.DetallePedido;
import com.varcal.ecommer.Models.DireccionUsuario;
import com.varcal.ecommer.Models.EstadoCarrito;
import com.varcal.ecommer.Models.EstadoPedido;
import com.varcal.ecommer.Models.Pedido;
import com.varcal.ecommer.Models.Usuario;
import com.varcal.ecommer.Repos.CarritoRepository;
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

    public PedidoService(PedidoRepository pedidoRepository,
            CarritoRepository carritoRepository,
            DetallePedidoRepository detallePedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.carritoRepository = carritoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }

    @Transactional
    public Pedido crearPedidoDesdeCarrito(Integer idCarrito, String metodoPago, DireccionUsuario direccion) {
        Carrito carrito = carritoRepository.findById(idCarrito)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        if (carrito.getDetalles().isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        // Calcular el total del carrito
        double total = 0.0;
        for (CarritoDetalle det : carrito.getDetalles()) {
            BigDecimal precioUnitario = det.getPrecioUnitario();
            if (precioUnitario == null || precioUnitario.doubleValue() == 0) {
                // ✅ Usa el precio base del producto o variante
                if (det.getVariante() != null) {
                    precioUnitario = det.getVariante().getPrecioAjuste() != null
                            ? det.getVariante().getPrecioAjuste()
                            : det.getProducto().getPrecio();
                } else {
                    precioUnitario = det.getProducto().getPrecio();
                }
                det.setPrecioUnitario(precioUnitario);
            }
            total += precioUnitario.doubleValue() * det.getCantidad();
        }

        // Crear pedido
        Pedido pedido = Pedido.builder()
                .usuario(carrito.getUsuario())
                .direccionEntrega(direccion)
                .estado(EstadoPedido.PENDIENTE)
                .metodoPago(metodoPago)
                .fechaPedido(LocalDateTime.now())
                .total(total)
                .build();

        pedidoRepository.save(pedido);

        // Crear los detalles del pedido
        List<DetallePedido> detalles = carrito.getDetalles().stream().map(det -> {
            double precioUnitario = det.getPrecioUnitario().doubleValue();
            double subtotal = precioUnitario * det.getCantidad();

            return DetallePedido.builder()
                    .pedido(pedido)
                    .producto(det.getProducto())
                    .variante(det.getVariante())
                    .cantidad(det.getCantidad())
                    .precioUnitario(precioUnitario)
                    .subtotal(subtotal)
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
