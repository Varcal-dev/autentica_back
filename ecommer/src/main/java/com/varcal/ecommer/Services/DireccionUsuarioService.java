package com.varcal.ecommer.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varcal.ecommer.Models.DireccionUsuario;
import com.varcal.ecommer.Models.Usuario;
import com.varcal.ecommer.Repos.DireccionUsuarioRepository;
import com.varcal.ecommer.Repos.UsuarioRepository;

import java.util.List;

@Service
public class DireccionUsuarioService {

    @Autowired
    private DireccionUsuarioRepository direccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<DireccionUsuario> listarPorUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return direccionRepository.findByUsuario(usuario);
    }

    public DireccionUsuario crear(Long idUsuario, DireccionUsuario direccion) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        direccion.setUsuario(usuario);
        return direccionRepository.save(direccion);
    }

    public void eliminar(Long idDireccion) {
        direccionRepository.deleteById(idDireccion);
    }
}
