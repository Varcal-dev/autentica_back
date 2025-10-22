package com.varcal.ecommer.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varcal.ecommer.Models.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByActivoTrue();
    List<Producto> findByCategoriaIdCategoria(Long idCategoria);
}
