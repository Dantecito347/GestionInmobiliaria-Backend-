package com.utn.gestioninmobiliaria.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "zonas")
@Data
public class Zona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona")
    private Integer idZona;

    @Column(name = "nombre_barrio", nullable = false, length = 100)
    private String nombreBarrio;

    @Column(name = "ciudad", nullable = false, length = 100)
    private String Ciudad;

    @Column(name = "zona", nullable = false, length = 100)
    private String zona;
}
