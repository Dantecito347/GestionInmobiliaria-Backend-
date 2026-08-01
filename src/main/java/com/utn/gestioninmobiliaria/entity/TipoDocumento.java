package com.utn.gestioninmobiliaria.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipos_documentos")
@Data
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_doc")
    private Integer idTipoDoc;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer idTipoDoc, String descripcion) {
        this.idTipoDoc = idTipoDoc;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(Integer idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

