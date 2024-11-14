package com.padron.padron.entities;




import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idusuario;

     @Column(length = 8, nullable = false)
    private String dni;

    @Column(length = 60, nullable = false)
    private String apellidos;

    @Column(length = 60, nullable = false)
    private String nombres;

    @Column(length = 15, nullable = false)
    private String clave;

    @Column(length = 1, nullable = false)
    private int tipo;

    @Column(length = 1, nullable = false)
    @ColumnDefault("1")
    private int estado;

}
