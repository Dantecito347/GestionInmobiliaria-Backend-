package com.utn.gestioninmobiliaria.dto;

import lombok.Data;

@Data
public class PersonaResponseDTO {
    private Long idPersona;
    private String nombre;
    private String apellido;
    private Integer idTipoDoc;
    private String tipoDocumentoDescripcion;
    private String nroDocumento;
    private String cuilCuit;
    private String telefono;
    private String email;
    private String cbuAlias;
}
