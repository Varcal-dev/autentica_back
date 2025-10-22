package com.varcal.ecommer.Controllers;

import org.springframework.web.bind.annotation.*;

import com.varcal.ecommer.Models.Usuario;
import com.varcal.ecommer.Repos.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String status() {
        return "✅ Backend Auténtica conectado correctamente a la base de datos.";
    }

    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }
}