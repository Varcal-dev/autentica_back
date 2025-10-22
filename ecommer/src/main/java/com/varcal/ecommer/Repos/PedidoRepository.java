package com.varcal.ecommer.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varcal.ecommer.Models.Pedido;
import com.varcal.ecommer.Models.Usuario;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByUsuario_IdUsuario(Integer idUsuario);

    void deleteById(Integer usuarioId);

    List<Pedido> findByUsuarioOrderByFechaPedidoDesc(Usuario usuario);
}