package com.varcal.ecommer.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.varcal.ecommer.Models.Cupon;
import com.varcal.ecommer.Services.CuponService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cupones")
@RequiredArgsConstructor
public class CuponController {

    private final CuponService cuponService;

    @GetMapping("/validar")
    public ResponseEntity<Cupon> validarCupon(
            @RequestParam String codigo,
            @RequestParam BigDecimal totalCompra
    ) {
        return ResponseEntity.ok(cuponService.validarCupon(codigo, totalCompra));
    }
}