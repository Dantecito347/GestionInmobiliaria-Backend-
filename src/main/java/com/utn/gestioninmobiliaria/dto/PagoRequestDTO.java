package com.utn.gestioninmobiliaria.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagoRequestDTO {
    @NotNull(message = "El ID del Pago debe de ser obligatorio.")
    private Integer idContrato;

    @NotNull(message = "El mes de cobuertura es obligatorio.")
    private Integer mesCobertura;

    @NotNull(message = "El año de cobertura es obligatorio.")
    private Integer anioCobertura;

    @NotNull(message = "El monto obligatorio es requerido.")
    private BigDecimal montoObligatorio;

    @NotNull(message = "El monto pagado es requerido.")
    private BigDecimal montoPagado;

    private LocalDate fechaPago;
    private String estadoPago;
}
