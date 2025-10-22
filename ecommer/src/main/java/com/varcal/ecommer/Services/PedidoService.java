package com.varcal.ecommer.Services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.varcal.ecommer.Models.Pedido;
import com.varcal.ecommer.Repos.PedidoRepository;

import java.util.List;

@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizarEstado(Long id, String estado) {
        Pedido pedido = pedidoRepository.findByUsuarioId(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
