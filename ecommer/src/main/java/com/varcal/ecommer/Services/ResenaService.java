package com.varcal.ecommer.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.varcal.ecommer.Models.Producto;
import com.varcal.ecommer.Models.Resena;
import com.varcal.ecommer.Models.Usuario;
import com.varcal.ecommer.Repos.ResenaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResenaService {

    private final ResenaRepository resenaRepository;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;

    public Resena crearResena(Long idProducto, Long idUsuario, String comentario, Integer calificacion) {
        Producto producto = productoService.obtenerPorId(idProducto);
        Usuario usuario = usuarioService.obtenerPorId(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Resena resena = Resena.builder()
                .producto(producto)
                .usuario(usuario)
                .comentario(comentario)
                .calificacion(calificacion)                
                .build();

        return resenaRepository.save(resena);
    }

    public List<Resena> listarResenasPorProducto(Long idProducto) {
        Producto producto = productoService.obtenerPorId(idProducto);
        return resenaRepository.findByProducto(producto);
    }

    public List<Resena> listarResenasPorUsuario(Long idUsuario) {
    Usuario usuario = usuarioService.obtenerPorId(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    return resenaRepository.findByUsuario(usuario);
}


}