package com.varcal.ecommer.Models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "variantes_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VarianteProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVariante;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    private String nombre;
    private String codigoSku;
    private BigDecimal precioAjuste;
    private Integer stock;
    private Boolean activo = true;
}