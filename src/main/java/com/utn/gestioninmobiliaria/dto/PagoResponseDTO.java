package com.utn.gestioninmobiliaria.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagoResponseDTO {
    private Integer idPago;
    private Integer idContrato;
    private Integer mesContrato;
    private Integer anioCobertura;
    private BigDecimal montoObligatorio;
    private BigDecimal montoPagado;
    private LocalDate fechaPago;
    private String estadoPago;

    private String direccionPropiedad;
    private String nombreInquilino;
    private String apellidoInquilino;
}
