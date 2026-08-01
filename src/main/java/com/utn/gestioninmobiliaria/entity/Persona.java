package com.utn.gestioninmobiliaria.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "personas")
@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "id_tipo_doc", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "nro_documento", nullable = false, length = 20)
    private String nroDocumento;

    @Column(name = "cuil_cuit", length = 20)
    private String cuilCuit;

    @Column(name = "telefono", length = 50)
    private String telefono;

    @Column(name = "email",length = 100)
    private String email;

    @Column(name = "cbu_alias", length = 100)
    private String cbuAlias;

    @Column(name = "fecha_alta_sistema")
    private LocalDateTime fechaAltaSistema = LocalDateTime.now();

    @Column(name = "activo",nullable = false)
    private Boolean activo = true;
}
