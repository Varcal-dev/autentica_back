package com.varcal.ecommer.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varcal.ecommer.Models.Carrito;
import com.varcal.ecommer.Models.CarritoDetalle;

public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Long> {
    List<CarritoDetalle> findByCarrito(Carrito carrito);
}
