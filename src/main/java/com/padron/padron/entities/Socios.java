package com.padron.padron.entities;


import java.time.LocalDate;


import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data 
@Entity
public class Socios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idsocio;
    @Column(length = 9 , nullable = false)
    private String dni;
    @Column(length = 60 , nullable = false)
    private String nombre;
    @Column(length = 60 , nullable = false)
    private String apellidoP;
    @Column(length = 60 , nullable = false)
    private String apellidoM;
    @Column(length = 60 , nullable = false)
    private String correo;
    @Column(length = 15 , nullable = false)
    private String telefono;
    @Column(length = 60 , nullable = false)
    private String direccion;
    @Column(length = 20 , nullable = false)
    private LocalDate fechaNacimiento;
    @Column(length = 60 , nullable = false)
    private String ocupacion;
    @Column(length = 1 , nullable = false)
    private char genero;
    @Column(length = 20 , nullable = false)
    private LocalDate fechaAfiliacion;
    @Column(length = 1 , nullable = false)
    @ColumnDefault("1")
    private int estado;
    @Column(length = 1 , nullable = false)
    private int tipo;




    
    
    
}
