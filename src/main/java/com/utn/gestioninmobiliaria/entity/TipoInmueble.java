package com.utn.gestioninmobiliaria.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipos_inmueble")
@Data
public class TipoInmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;
}
