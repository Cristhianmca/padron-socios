package com.padron.padron.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pagos_membresia")
public class PagosMembresia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpago; // Identificador del pago

    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    private Socios socio; // Relación con el socio, clave foránea

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago; // Fecha del pago

    @Column(name = "monto", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(name = "metodo_pago", length = 50, nullable = false)
    private String metodoPago; // Método de pago: 'Tarjeta', 'Efectivo', etc.

    @Column(name = "estado_pago", length = 1, nullable = false)
    private char estadoPago; // Estado del pago: '1' (completado), '2' (pendiente), etc.

}
