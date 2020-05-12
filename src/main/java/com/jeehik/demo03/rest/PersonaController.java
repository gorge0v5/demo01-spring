package com.jeehik.demo03.rest;

import com.jeehik.demo03.model.dao.PersonaDao;
import com.jeehik.demo03.model.entity.Persona;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    private PersonaDao personaDao;

    public PersonaController(PersonaDao personaDao){
        this.personaDao = personaDao;
    }

    @GetMapping
    public List<Persona> listarTodos(){
        return personaDao.listarTodos();
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("It is working...");
    }

    @GetMapping("/nombre/{param}")
    public List<Persona> buscarPersona(@PathVariable String param){
        return personaDao.buscarPersona(param);
    }

    @GetMapping("/idPersona/{idPersona}")
    public Persona getBuscarPorId(@PathVariable int idPersona){
        return personaDao.buscaPersonaPorId(idPersona);
    }

    @PostMapping
    public Persona registrarPersona(@RequestBody Persona p) {
        p.setIdPersona(personaDao.setInsertarPersona(p));
        return p;
    }

    @PutMapping
    public Persona actualizarPersona(@RequestBody Persona p) {
        if(personaDao.setActualizarPersona(p)) return p;
        else return p = null;
    }

    @DeleteMapping("/idPersona/{idPersona}")
    public List<Persona> eliminarPersona(@PathVariable int idPersona) {
        if (personaDao.setEliminarPersona(idPersona)) {
            return personaDao.listarTodos();
        } else {
            return personaDao.listarTodos();
        }
    }

    //@DeleteMapping("/idPersona/{idPersona}")
    //public boolean eliminarPersona(@PathVariable int idPersona) {
    //  return personaDao.setEliminarPersona(idPersona);
    //}

}
