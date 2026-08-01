package com.utn.gestioninmobiliaria.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "propiedades")
@Data
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propiedad")
    private Integer idPropiedad;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "activo")
    private Boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario", nullable = false)
    private Persona propietario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo")
    private TipoInmueble tipoInmueble;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zona")
    private Zona zonas;
}
