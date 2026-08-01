package com.utn.gestioninmobiliaria.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PersonaRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100)
    private String apellido;

    @NotNull(message = "El tipo de documento es obligatorio")
    private Integer idTipoDoc;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(max = 20)
    private String nroDocumento;

    private String cuilCuit;
    
    private String telefono;
    
    @Email(message = "El formato del email no es válido")
    private String email;
    
    private String cbuAlias;
}
