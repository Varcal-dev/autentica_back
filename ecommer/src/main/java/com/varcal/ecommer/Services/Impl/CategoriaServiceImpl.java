package com.varcal.ecommer.Services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.varcal.ecommer.Models.Categoria;
import com.varcal.ecommer.Repos.CategoriaRepository;
import com.varcal.ecommer.Services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria crear(Categoria categoria) {
        if (categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizar(Long id, Categoria categoria) {
        Categoria existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        existente.setNombre(categoria.getNombre());
        existente.setDescripcion(categoria.getDescripcion());
        existente.setEstado(categoria.getEstado());
        return categoriaRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}