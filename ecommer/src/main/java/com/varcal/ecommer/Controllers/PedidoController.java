package com.varcal.ecommer.Controllers;

import org.springframework.web.bind.annotation.*;

import com.varcal.ecommer.Models.DireccionUsuario;
import com.varcal.ecommer.Models.Pedido;
import com.varcal.ecommer.Models.Usuario;
import com.varcal.ecommer.Services.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/crear")
    public Pedido crearPedido(@RequestParam Integer idCarrito,
                              @RequestParam String metodoPago,
                              @RequestBody DireccionUsuario direccion) {
        return pedidoService.crearPedidoDesdeCarrito(idCarrito, metodoPago, direccion, metodoPago);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Pedido> obtenerPedidosPorUsuario(@PathVariable Integer idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        return pedidoService.obtenerPedidosPorUsuario(usuario);
    }
}