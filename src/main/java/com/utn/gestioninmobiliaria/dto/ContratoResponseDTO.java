package com.utn.gestioninmobiliaria.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ContratoResponseDTO {
    private Integer idContrato;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal valorInicial;
    private Integer idAjuste;
    private String estado;

    private Long idInquilino;
    private String nombreInquilino;
    private String apellidoInquilino;

    private Integer idPropiedad;
    private String direccionPropiedad;

    private List<ObligacionResponseDTO> obligaciones;
}
