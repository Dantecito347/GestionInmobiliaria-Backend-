package com.utn.gestioninmobiliaria.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContratoRequestDTO {
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @NotNull(message = "El valor es obligatorio")
    private BigDecimal valorInicial;

    private Integer idAjuste;
    private String estado;

    @NotNull(message = "La ID del Inquilino es obligatoria")
    private Long idInquilino;

    @NotNull(message = "La ID de la propiedad es obligatoria")
    private Integer idPropiedad;

}
