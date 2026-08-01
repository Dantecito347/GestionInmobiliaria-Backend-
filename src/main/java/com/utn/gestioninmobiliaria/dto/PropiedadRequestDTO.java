package com.utn.gestioninmobiliaria.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PropiedadRequestDTO {
    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;

    private Integer idTipo;

    private String estado;

    private Boolean activo = true;

    @NotNull(message = "La identificacion del propietario es obligatoria")
    private Long idPropietario;

    private Integer idZona;
}
