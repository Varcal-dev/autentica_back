package com.varcal.ecommer.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cupones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCupon;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Enumerated(EnumType.STRING)
    private TipoCupon tipo; // PORCENTAJE o VALOR_FIJO

    @Column(nullable = false)
    private BigDecimal valor; // Valor o porcentaje del descuento

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    private Integer usosMaximos;
    private Integer usosActuales = 0;

    private BigDecimal minimoCompra;
    private Boolean activo = true;
}