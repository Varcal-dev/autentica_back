package com.varcal.ecommer.Controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.varcal.ecommer.Models.Resena;
import com.varcal.ecommer.Services.ResenaService;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    @PostMapping("/crear")
    public ResponseEntity<Resena> crearResena(
            @RequestParam Long idProducto,
            @RequestParam Long idUsuario,
            @RequestParam Integer calificacio,
            @RequestParam(required = false) String comentario) {

        return ResponseEntity.ok(
                resenaService.crearResena(idProducto, idUsuario, comentario, calificacio)
        );
    }

    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<Resena>> listarPorProducto(@PathVariable Long idProducto) {
        return ResponseEntity.ok(resenaService.listarResenasPorProducto(idProducto));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Resena>> listarPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(resenaService.listarResenasPorUsuario(idUsuario));
    }
}
