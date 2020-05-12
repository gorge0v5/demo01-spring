package com.jeehik.demo03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.sql.DataSource;

@Configuration  // anotación que define una clase como configuración al contexto de Spring(para INSERT)
public class PersistenciaConfig {

    @Bean("insertarPersona")
    public SimpleJdbcInsert insertarPersona(DataSource dataSource){
        return new SimpleJdbcInsert(dataSource).withTableName("Persona").usingGeneratedKeyColumns("idPersona");
    }

}
