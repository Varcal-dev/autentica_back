package com.varcal.ecommer.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.varcal.ecommer.Models.ImagenProducto;

import java.util.List;

@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Long> {
    List<ImagenProducto> findByProductoIdProducto(Long idProducto);
}