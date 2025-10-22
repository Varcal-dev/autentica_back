package com.varcal.ecommer.Models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "direcciones_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDireccion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    private String direccion;
    private String ciudad;
    private String departamento;
    private String codigoPostal;
    private Boolean esPrincipal = false;
    private Boolean activo = true;

    private LocalDateTime fechaRegistro = LocalDateTime.now();
}
