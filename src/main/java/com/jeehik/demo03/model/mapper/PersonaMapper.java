package com.jeehik.demo03.model.mapper;

import com.jeehik.demo03.model.entity.Persona;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonaMapper implements RowMapper<Persona> {

    // es un mapeo de filas de base de datos y se parsea y se crea a partir de esto objetos de tipo persona.
    @Override
    public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Sirve para crear el objeto Persona, agregandole información mediante el constructor.
        final Persona p = new Persona(rs.getInt("idPersona"), rs.getString("nombre"), rs.getInt("sexo"));
        return p;

    }
}
