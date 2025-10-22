package com.varcal.ecommer.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varcal.ecommer.Models.Producto;
import com.varcal.ecommer.Models.Resena;
import com.varcal.ecommer.Models.Usuario;

public interface ResenaRepository extends JpaRepository<Resena, Integer> {
    List<Resena> findByProducto(Producto producto);
    List<Resena> findByUsuario(Usuario usuario);
}