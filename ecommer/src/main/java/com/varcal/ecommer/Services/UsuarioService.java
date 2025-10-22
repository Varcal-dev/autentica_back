package com.varcal.ecommer.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varcal.ecommer.Models.Usuario;
import com.varcal.ecommer.Repos.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
    return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
}


    public Usuario crear(Usuario usuario) {
        usuario.setFechaRegistro(java.time.LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setTelefono(usuarioActualizado.getTelefono());
            usuario.setGenero(usuarioActualizado.getGenero());
            usuario.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}