package com.varcal.ecommer.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varcal.ecommer.Models.Pedido;
import com.varcal.ecommer.Models.Usuario;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByUsuarioId(Long usuarioId);

    void deleteById(Integer usuarioId);
}