package com.varcal.ecommer.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varcal.ecommer.Models.Cupon;

public interface CuponRepository extends JpaRepository<Cupon, Integer> {
    Optional<Cupon> findByCodigo(String codigo);
}