package com.fernandoyutiz.jasperlist;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Persona {
    private String nombre;
    private String apellido;
    private Integer edad;
    private List<String> profesiones;
    private String error;
}
