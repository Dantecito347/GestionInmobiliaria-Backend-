package com.utn.gestioninmobiliaria.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ZonaRequestDTO {
    @NotBlank(message = "El nombre del barrio no puede estar vacío")
    private String nombreBarrio;

    @NotBlank(message = "La ciudad no puede estar vacía")
    private String ciudad;

    @NotBlank(message = "La zona no debe estar vacia")
    private String zona;
}
