package com.utn.gestioninmobiliaria.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagos")
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    @Column(name = "mes_cobertura", nullable = false)
    private Integer mesCobertura;

    @Column(name = "anio_cobertura", nullable = false)
    private Integer anioCobertura;

    @Column(name = "monto_obligatorio", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoObligatorio;

    @Column(name = "monto_pagado", nullable = false, precision = 12, scale = 2)
    private BigDecimal montoPagado;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "estado_pago", length = 50)
    private String estadoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato", nullable = false)
    private Contrato contrato;

}
