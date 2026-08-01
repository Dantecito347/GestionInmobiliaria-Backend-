package com.utn.gestioninmobiliaria.mapper;
import com.utn.gestioninmobiliaria.dto.PagoRequestDTO;
import com.utn.gestioninmobiliaria.dto.PagoResponseDTO;
import com.utn.gestioninmobiliaria.entity.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagoMapper {
    @Mapping(source = "contrato.idContrato", target = "idContrato")
    @Mapping(source = "contrato.propiedad.direccion", target = "direccionPropiedad")
    @Mapping(source = "contrato.inquilino.nombre", target = "nombreInquilino")
    @Mapping(source = "contrato.inquilino.apellido", target = "apellidoInquilino")
    @Mapping(target = "mesContrato", ignore = true)
    PagoResponseDTO toResponseDTO(Pago pago);

    java.util.List<PagoResponseDTO> toResponseDTOList(java.util.List<Pago>pagos);

    @Mapping(target = "idPago", ignore = true)
    @Mapping(target = "contrato", ignore = true)
    Pago toEntity(PagoRequestDTO requestDTO);
}
