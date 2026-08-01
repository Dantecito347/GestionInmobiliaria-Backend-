package com.utn.gestioninmobiliaria.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipos_ajuste")
@Data
public class TipoAjuste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ajuste")
    private Integer idAjuste;

    @Column(name = "descripcion")
    private String descripcion;
}
