package com.varcal.ecommer.Services;

import java.util.List;
import java.util.Optional;

import com.varcal.ecommer.Models.Categoria;

public interface CategoriaService {
    List<Categoria> listar();
    Optional<Categoria> obtenerPorId(Long id);
    Categoria crear(Categoria categoria);
    Categoria actualizar(Long id, Categoria categoria);
    void eliminar(Long id);
}