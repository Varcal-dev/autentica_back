package com.varcal.ecommer.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_direccion", nullable = false)
    private DireccionUsuario direccionEntrega;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    private Double total;
    private String metodoPago;

    private LocalDateTime fechaPedido = LocalDateTime.now();
    private LocalDateTime fechaActualizacion;
    private LocalDateTime fechaEntrega;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles;
}

enum EstadoPedido { PENDIENTE, PAGADO, EN_PROCESO, ENVIADO, ENTREGADO, CANCELADO }