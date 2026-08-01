package com.utn.gestioninmobiliaria.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "perfiles")
@Data
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Integer idPerfil;

    @Column(name = "nombre_perfil")
    private String nombrePerfil;
}
