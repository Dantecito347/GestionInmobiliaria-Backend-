package com.utn.gestioninmobiliaria.dto;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ObligacionRequestDTO {
    private Integer idContrato;
    private String descripcion;
    private BigDecimal importeReferencia;
    private Boolean pagadoPorInquilino;
}
