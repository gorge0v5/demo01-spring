package com.jeehik.demo03.model.dao;

import com.jeehik.demo03.model.entity.Persona;
import com.jeehik.demo03.model.mapper.PersonaMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Collections;
import java.util.List;

@Repository   // define componente para acceso a la base de datos
public class PersonaDao {
    private JdbcTemplate template;
    private PersonaMapper personaMapper;
    private SimpleJdbcInsert insertarPersona;

    // Implementando un constructor
    public  PersonaDao(JdbcTemplate template, PersonaMapper personaMapper, SimpleJdbcInsert insertarPersona){
        this.template = template;
        this.personaMapper = personaMapper;
        this.insertarPersona = insertarPersona;
    }

    public List<Persona> listarTodos(){
        return template.query("SELECT * FROM Persona", personaMapper);
    }

    public List<Persona> buscarPersona(String param){
        return template.query("SELECT * FROM Persona where nombre like ? ", new Object[] {String.format("%%%s%%", param)}, personaMapper);
    }

    public Persona buscaPersonaPorId(int idPersona){
        // Si solo se quiere un objeto  se poner queryFOrObject
        return template.queryForObject("SELECT * FROM Persona where idPersona = ? ", new Object[] {idPersona}, personaMapper);
    }

    public int setInsertarPersona(Persona p) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("nombre", p.getNombre(), Types.VARCHAR);
        in.addValue("sexo", p.getSexo(), Types.INTEGER);
        return insertarPersona.executeAndReturnKey(in).intValue();
    }

    public boolean setActualizarPersona(Persona p) {
        int filasAfectadas = template.update("UPDATE Persona SET nombre = ?, sexo = ? WHERE idPersona = ?",
                new Object[]{p.getNombre(), p.getSexo(), p.getIdPersona()},
                new int[]{Types.VARCHAR, Types.TINYINT, Types.INTEGER});

        if(filasAfectadas > 0) return true;
        else return false;
    }

    public boolean setEliminarPersona(int idPersona) {
        int filasAfectadas = template.update("DELETE FROM Persona WHERE idPersona = ? ", Collections.singletonList(idPersona).toArray());

        if(filasAfectadas > 0) return true;
        else return false;
    }
}
