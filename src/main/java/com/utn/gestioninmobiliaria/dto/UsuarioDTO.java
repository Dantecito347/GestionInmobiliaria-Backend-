package com.utn.gestioninmobiliaria.dto;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer idUsuario;
    private String username;
    private Integer idPerfil;
    private Boolean activo;
    private Long idPersona;
}
