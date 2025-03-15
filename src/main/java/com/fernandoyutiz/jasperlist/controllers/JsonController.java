package com.fernandoyutiz.jasperlist.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernandoyutiz.jasperlist.Errores;
import com.fernandoyutiz.jasperlist.Persona;
import com.fernandoyutiz.jasperlist.dto.TestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.eclipse.jdt.internal.compiler.parser.Parser.name;

@RestController
@RequestMapping("/api/v1")

public class JsonController {
    private static final Boolean error = false;

    @GetMapping("/json")
    public ResponseEntity<Persona> helloJson(){
        Persona persona = new Persona();
        persona.setApellido("Yutiz");
        persona.setEdad(55);
        persona.setNombre("Fernando");
        List<String> profesiones = new ArrayList<>();
        profesiones.add("Programador");
        profesiones.add("Tecnico Electronico");
        profesiones.add("Seguridad Informatica");
        persona.setProfesiones(profesiones);
        if(error){
            persona.setError("Ocurrio un error");
            return ResponseEntity.internalServerError().body(persona);
        }
        return ResponseEntity.ok(persona);
    }

    @GetMapping("/generico")
    public ResponseEntity<?> generico(){
        Persona persona = new Persona();
        persona.setApellido("Yutiz");
        persona.setEdad(55);
        persona.setNombre("Fernando");
        List<String> profesiones = new ArrayList<>();
        profesiones.add("Programador");
        profesiones.add("Tecnico Electronico");
        profesiones.add("Seguridad Informatica");
        persona.setProfesiones(profesiones);
        if(error){
            Errores errores = new Errores();
            errores.setNumError("1");
            errores.setDetailError("Error UNO");
            return ResponseEntity.internalServerError().body(errores);
        }
        return ResponseEntity.ok(persona);
    }

    @GetMapping("/setjson")
    public String setJson(@RequestBody()Map<String,Object> json){
        return json.toString();
    }

    @GetMapping("/testdto")
    public ResponseEntity<TestDTO> testdto(){
        Map<String, Object> ds = new HashMap<>();
        ds.put("lastname", "Yutiz");
        ds.put("firstname", "Fernando");
        ds.put("age", 55);
        //ds.put("profession", "Programador");
        ds.put("excluir", "EXCLUIDO" );
        ObjectMapper mapper = new ObjectMapper();
        TestDTO dto = mapper.convertValue(ds, TestDTO.class);
        return ResponseEntity.ok(dto);
    }
}
