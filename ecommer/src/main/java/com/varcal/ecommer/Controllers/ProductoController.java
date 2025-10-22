package com.varcal.ecommer.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.varcal.ecommer.Models.Producto;
import com.varcal.ecommer.Services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listar() {
        return productoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{idCategoria}")
    public List<Producto> listarPorCategoria(@PathVariable Long idCategoria) {
        return productoService.listarPorCategoria(idCategoria);
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoService.crear(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}