package com.varcal.ecommer.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varcal.ecommer.Models.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
}
