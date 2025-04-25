package com.fernandoyutiz.jasperlist.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestDTO {

    @Getter
    @JsonAlias("firstname")
    private String nombreDTO;

    @Getter
    @JsonAlias("lastname")
    private String apellidoDTO;

    @Getter
    @JsonAlias("age")
    private Integer edadDTO;

    @Getter
    @JsonAlias("profession")
    private String profesionDTO;

    @JsonAlias("excluir")
    private String excluirDTO;

    public String getExcluirDTO() {
        return null;
    }

    @Getter
    @JsonAlias("direcciones_dto")
    private List<DireccionDTO> direcciones;

    @Getter
    @JsonAlias("hobbies")
    private List<String> hobbiesDTO;

}
