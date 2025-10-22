package com.varcal.ecommer.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.varcal.ecommer.Models.Cupon;
import com.varcal.ecommer.Models.TipoCupon;
import com.varcal.ecommer.Repos.CuponRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CuponService {

    private final CuponRepository cuponRepository;

    public Cupon validarCupon(String codigo, BigDecimal totalCompra) {
        Cupon cupon = cuponRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Cupón no encontrado"));

        // Validar si está activo
        if (!Boolean.TRUE.equals(cupon.getActivo())) {
            throw new RuntimeException("El cupón no está activo");
        }

        // Validar fechas
        LocalDateTime ahora = LocalDateTime.now();
        if (cupon.getFechaInicio() != null && ahora.isBefore(cupon.getFechaInicio())) {
            throw new RuntimeException("El cupón aún no está disponible");
        }
        if (cupon.getFechaFin() != null && ahora.isAfter(cupon.getFechaFin())) {
            throw new RuntimeException("El cupón ha expirado");
        }

        // Validar uso máximo
        if (cupon.getUsosMaximos() != null && cupon.getUsosActuales() >= cupon.getUsosMaximos()) {
            throw new RuntimeException("El cupón alcanzó su límite de uso");
        }

        // Validar monto mínimo
        if (cupon.getMinimoCompra() != null && totalCompra.compareTo(cupon.getMinimoCompra()) < 0) {
            throw new RuntimeException("El pedido no cumple el mínimo de compra");
        }

        return cupon;
    }

    public BigDecimal aplicarDescuento(Cupon cupon, BigDecimal total) {
        if (cupon.getTipo() == TipoCupon.PORCENTAJE) {
            BigDecimal descuento = total.multiply(cupon.getValor()).divide(BigDecimal.valueOf(100));
            return total.subtract(descuento);
        } else {
            return total.subtract(cupon.getValor());
        }
    }

    public void incrementarUso(Cupon cupon) {
        cupon.setUsosActuales(cupon.getUsosActuales() + 1);
        cuponRepository.save(cupon);
    }
}
