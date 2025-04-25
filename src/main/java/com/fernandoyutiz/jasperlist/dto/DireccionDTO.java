package com.fernandoyutiz.jasperlist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DireccionDTO {
    private String calleDTO;
    private String numeroDTO;
    private String pisoDTO;
    private String deptoDTO;
    private String barrioDTO;
}
