package com.varcal.ecommer.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carrito_detalle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrito", nullable = false)
    @JsonBackReference
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_variante")
    private VarianteProducto variante;

    private Integer cantidad;
    private BigDecimal precioUnitario;

    @Column(name = "fecha_agregado")
    private LocalDateTime fechaAgregado = LocalDateTime.now();
}