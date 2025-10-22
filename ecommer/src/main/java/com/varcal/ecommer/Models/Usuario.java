package com.varcal.ecommer.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    private String telefono;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    private EstadoUsuario estado;

    private LocalDate fechaNacimiento;
    private Genero genero;
    private LocalDateTime ultimoAcceso;
    private LocalDateTime fechaRegistro = LocalDateTime.now();
    private LocalDateTime fechaEliminacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DireccionUsuario> direcciones;

}
