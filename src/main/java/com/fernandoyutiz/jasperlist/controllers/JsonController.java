package com.fernandoyutiz.jasperlist.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernandoyutiz.jasperlist.Errores;
import com.fernandoyutiz.jasperlist.Persona;
import com.fernandoyutiz.jasperlist.dto.DireccionDTO;
import com.fernandoyutiz.jasperlist.dto.TestDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")

public class JsonController {
    private static final Boolean error = false;

    @CrossOrigin
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

    @CrossOrigin(origins = {"http://127.0.0.1:5500"})
    @GetMapping(value = "/testdto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestDTO> testdto(){
        List<String> hobbies = new ArrayList<>();
        hobbies.add("programacion");
        hobbies.add("electronica");
        hobbies.add("musica");

        Map<String, Object> ds = new HashMap<>();
        ds.put("lastname", "Yutiz");
        ds.put("firstname", "Fernando Daniel");
        ds.put("age", 55);
        ds.put("profession", "Programador");
        ds.put("excluir", "EXCLUIDO" );
        ds.put("hobbies", hobbies);

        List<DireccionDTO> direccionDTOList = new ArrayList<>();
        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setCalleDTO("Juan Agustin Garcia");
        direccionDTO.setNumeroDTO("2752");
        direccionDTO.setPisoDTO("7");
        direccionDTO.setDeptoDTO("B");
        direccionDTO.setBarrioDTO("Villa Santa Rita");
        direccionDTOList.add(direccionDTO);
        DireccionDTO direccionDTO2 = new DireccionDTO();
        direccionDTO2.setCalleDTO("San Blas");
        direccionDTO2.setNumeroDTO("2984");
        direccionDTO2.setPisoDTO("6");
        direccionDTO2.setDeptoDTO("C");
        direccionDTOList.add(direccionDTO2);
        ds.put("direcciones_dto", direccionDTOList);
        TestDTO dto1 = new ObjectMapper().convertValue(ds, TestDTO.class);
        return ResponseEntity.ok(dto1);
    }
}
