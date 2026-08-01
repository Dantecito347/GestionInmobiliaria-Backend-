package com.utn.gestioninmobiliaria.dto;
import lombok.Data;

@Data
public class PropiedadResponseDTO {
    private Integer idPropiedad;
    private String direccion;
    private String estado;
    private Boolean activo;
    private Long idPropietario;
    private String nombrePropietario;
    private String apellidoPropietario;
    private Long idTipo;
    private String tipoDescripcion;
    private Integer idZona;
    private String nombreBarrio;
    private String zona;
}
