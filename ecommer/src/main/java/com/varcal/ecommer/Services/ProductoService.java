package com.varcal.ecommer.Services;

import java.util.List;
import java.util.Optional;

import com.varcal.ecommer.Models.Producto;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> obtenerPorId(Long id);
    List<Producto> listarPorCategoria(Long idCategoria);
    Producto crear(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}