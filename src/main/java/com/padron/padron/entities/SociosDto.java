package com.padron.padron.entities;

import java.time.LocalDate;



import lombok.Data;

@Data
public class SociosDto {

    private String dni;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String ocupacion;
    private char genero;
    private LocalDate fechaAfiliacion;
    private int estado;
    private int tipo;
}
