package com.varcal.ecommer.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.varcal.ecommer.Models.DireccionUsuario;
import com.varcal.ecommer.Services.DireccionUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
@CrossOrigin(origins = "*")
public class DireccionUsuarioController {

    @Autowired
    private DireccionUsuarioService direccionService;

    @GetMapping("/{idUsuario}")
    public List<DireccionUsuario> listarPorUsuario(@PathVariable Long idUsuario) {
        return direccionService.listarPorUsuario(idUsuario);
    }

    @PostMapping("/{idUsuario}")
    public DireccionUsuario crear(@PathVariable Long idUsuario, @RequestBody DireccionUsuario direccion) {
        return direccionService.crear(idUsuario, direccion);
    }

    @DeleteMapping("/{idDireccion}")
    public void eliminar(@PathVariable Long idDireccion) {
        direccionService.eliminar(idDireccion);
    }
}