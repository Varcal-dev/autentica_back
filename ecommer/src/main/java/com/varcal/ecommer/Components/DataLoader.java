package com.varcal.ecommer.Components;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.varcal.ecommer.Models.EstadoUsuario;
import com.varcal.ecommer.Models.Genero;
import com.varcal.ecommer.Models.Rol;
import com.varcal.ecommer.Models.Usuario;
import com.varcal.ecommer.Repos.UsuarioRepository;

//@Component
public class DataLoader {

    @Bean
    CommandLineRunner initData(UsuarioRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                Usuario u = Usuario.builder()
                        .nombre("Usuario Prueba")
                        .email("test@autentica.com")
                        .telefono("3001234567")
                        .password("123456") // 🔒 en el futuro se encriptará
                        .rol(Rol.ADMIN)
                        .estado(EstadoUsuario.ACTIVO)
                        .fechaNacimiento(LocalDate.of(1995, 5, 15))
                        .genero(Genero.M)
                        .ultimoAcceso(LocalDateTime.now())
                        .fechaRegistro(LocalDateTime.now())
                        .build();

                repo.save(u);
                System.out.println("✅ Usuario de prueba insertado correctamente en la base de datos.");
            } else {
                System.out.println("⚠️ Ya existen usuarios en la base de datos. No se insertó el usuario de prueba.");
            }
        };
    }

}