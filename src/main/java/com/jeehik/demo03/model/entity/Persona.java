package com.jeehik.demo03.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    private int idPersona;
    private String nombre;
    private int sexo;


}
