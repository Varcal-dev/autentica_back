package com.varcal.ecommer.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.varcal.ecommer.Models.VarianteProducto;

import java.util.List;

@Repository
public interface VarianteProductoRepository extends JpaRepository<VarianteProducto, Long> {
    List<VarianteProducto> findByProductoIdProducto(Long idProducto);
}