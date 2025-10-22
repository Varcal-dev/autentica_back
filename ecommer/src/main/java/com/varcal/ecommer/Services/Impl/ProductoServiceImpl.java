package com.varcal.ecommer.Services.Impl;



import org.springframework.stereotype.Service;

import com.varcal.ecommer.Models.Producto;
import com.varcal.ecommer.Repos.ProductoRepository;
import com.varcal.ecommer.Services.ProductoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> listarPorCategoria(Long idCategoria) {
        return productoRepository.findByCategoriaIdCategoria(idCategoria);
    }

    @Override
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existente.setNombre(producto.getNombre());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        existente.setActivo(producto.getActivo());
        existente.setCategoria(producto.getCategoria());
        return productoRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}