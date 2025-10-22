package com.varcal.ecommer.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varcal.ecommer.Models.Carrito;
import com.varcal.ecommer.Models.EstadoCarrito;
import com.varcal.ecommer.Models.EstadoUsuario;
import com.varcal.ecommer.Models.Usuario;

import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    Optional<Carrito> findByUsuarioAndEstado(Usuario usuario, EstadoCarrito activo);
}
