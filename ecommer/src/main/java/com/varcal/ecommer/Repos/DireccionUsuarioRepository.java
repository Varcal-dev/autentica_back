package com.varcal.ecommer.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.varcal.ecommer.Models.DireccionUsuario;
import com.varcal.ecommer.Models.Usuario;

import java.util.List;

@Repository
public interface DireccionUsuarioRepository extends JpaRepository<DireccionUsuario, Long> {
    List<DireccionUsuario> findByUsuario(Usuario usuario);
}