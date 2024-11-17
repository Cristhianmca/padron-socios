package com.padron.padron.entities;



import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class BeneficioPorSocio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbeneficio")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false) // Asegúrate de que esto esté presente y bien configurado
    private Socios socio;

    @ManyToOne
    @JoinColumn(name = "beneficio_id", nullable = false)
    private BeneficioSocio beneficio;

     @Column(length = 1 , nullable = false)
    @ColumnDefault("1")
    private int estado;

    private LocalDate fechaAsignacion;
    private LocalDate fechaFin;

    
}
