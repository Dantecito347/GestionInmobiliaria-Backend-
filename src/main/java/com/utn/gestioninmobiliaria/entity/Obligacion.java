package com.utn.gestioninmobiliaria.entity;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "obligaciones")
@Data
public class Obligacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obligacion")
    private Integer idObligacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato", nullable = false)
    private Contrato contrato;

    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;

    @Column(name = "importe_referencia", nullable = false, precision = 10, scale = 2)
    private BigDecimal importeReferencia;

    @Column(name = "pagado_por_inquilino", nullable = false)
    private Boolean pagadoPorInquilino;
}
