package com.varcal.ecommer.Services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.varcal.ecommer.Models.Carrito;
import com.varcal.ecommer.Models.CarritoDetalle;
import com.varcal.ecommer.Models.EstadoCarrito;
import com.varcal.ecommer.Models.Producto;
import com.varcal.ecommer.Models.Usuario;
import com.varcal.ecommer.Models.VarianteProducto;
import com.varcal.ecommer.Repos.CarritoDetalleRepository;
import com.varcal.ecommer.Repos.CarritoRepository;
import com.varcal.ecommer.Repos.ProductoRepository;
import com.varcal.ecommer.Repos.UsuarioRepository;
import com.varcal.ecommer.Repos.VarianteProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final CarritoDetalleRepository detalleRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final VarianteProductoRepository varianteRepository;

    public Carrito obtenerOCrearCarrito(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        // Buscar carrito activo del usuario
        Optional<Carrito> existente = carritoRepository.findByUsuarioAndEstado(usuario, EstadoCarrito.ACTIVO);
        if (existente.isPresent()) {
            return existente.get();
        }

        // Si no existe, crear uno nuevo con el usuario asignado
        Carrito nuevo = new Carrito();
        nuevo.setUsuario(usuario);
        nuevo.setEstado(EstadoCarrito.ACTIVO);
        return carritoRepository.save(nuevo);
    }

    public Carrito agregarProducto(Long idUsuario, Long idProducto, Long idVariante, Integer cantidad) {
        // Obtener o crear el carrito
        Carrito carrito = obtenerOCrearCarrito(idUsuario);

        // Asegurar que el carrito está guardado (tiene ID válido)
        if (carrito.getIdCarrito() == null) {
            carrito = carritoRepository.save(carrito);
        }

        // Buscar el producto
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + idProducto));

        VarianteProducto variante = (idVariante != null)
                ? varianteRepository.findById(idVariante).orElse(null)
                : null;

        // Calcular el precio final
        BigDecimal precioUnitario = (variante != null)
                ? producto.getPrecio().add(variante.getPrecioAjuste())
                : producto.getPrecio();

        // Crear detalle
        CarritoDetalle detalle = new CarritoDetalle();
        detalle.setCarrito(carrito);
        detalle.setProducto(producto);
        detalle.setVariante(variante);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(precioUnitario);

        // Agregar detalle al carrito
        carrito.getDetalles().add(detalle);

        // Guardar carrito (cascade guarda detalle)
        carrito = carritoRepository.save(carrito);

        return carrito;
    }

    public Carrito eliminarProducto(Long idUsuario, Long idDetalle) {
        Carrito carrito = obtenerOCrearCarrito(idUsuario);
        carrito.getDetalles().removeIf(det -> det.getIdDetalle().equals(idDetalle));
        return carritoRepository.save(carrito);
    }

    public void vaciarCarrito(Long idUsuario) {
        Carrito carrito = obtenerOCrearCarrito(idUsuario);
        carrito.getDetalles().clear();
        carritoRepository.save(carrito);
    }
}
