package com.varcal.ecommer.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;

    private Boolean activo = true;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaEliminacion;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    @JsonBackReference
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<VarianteProducto> variantes;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ImagenProducto> imagenes;
}
