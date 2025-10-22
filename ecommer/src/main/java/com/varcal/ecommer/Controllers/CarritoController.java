package com.varcal.ecommer.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.varcal.ecommer.Models.Carrito;
import com.varcal.ecommer.Services.CarritoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(carritoService.obtenerOCrearCarrito(idUsuario));
    }

    @PostMapping("/agregar")
    public ResponseEntity<Carrito> agregarProducto(
            @RequestParam Long idUsuario,
            @RequestParam Long idProducto,
            @RequestParam(required = false) Long idVariante,
            @RequestParam Integer cantidad) {

        return ResponseEntity.ok(carritoService.agregarProducto(idUsuario, idProducto, idVariante, cantidad));
    }

    @DeleteMapping("/{idUsuario}/eliminar/{idDetalle}")
    public ResponseEntity<Carrito> eliminarProducto(@PathVariable Long idUsuario, @PathVariable Long idDetalle) {
        return ResponseEntity.ok(carritoService.eliminarProducto(idUsuario, idDetalle));
    }

    @DeleteMapping("/{idUsuario}/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long idUsuario) {
        carritoService.vaciarCarrito(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
