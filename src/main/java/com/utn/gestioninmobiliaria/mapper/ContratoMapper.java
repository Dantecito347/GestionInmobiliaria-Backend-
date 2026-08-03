package com.utn.gestioninmobiliaria.mapper;
import com.utn.gestioninmobiliaria.dto.ContratoRequestDTO;
import com.utn.gestioninmobiliaria.dto.ContratoResponseDTO;
import com.utn.gestioninmobiliaria.entity.Contrato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContratoMapper {
    @Mapping(source = "inquilino.idPersona", target = "idInquilino")
    @Mapping(source = "inquilino.nombre", target = "nombreInquilino")
    @Mapping(source = "inquilino.apellido", target = "apellidoInquilino")
    @Mapping(source = "propiedad.idPropiedad", target = "idPropiedad")
    @Mapping(source = "propiedad.direccion", target = "direccionPropiedad")
    @Mapping(source = "tipoAjuste.idAjuste", target = "idAjuste")
    ContratoResponseDTO toResponseDTO(Contrato contrato);

    java.util.List<ContratoResponseDTO> toResponseDTOList(java.util.List<Contrato> contratos);

    @Mapping(target = "idContrato", ignore = true)
    @Mapping(target = "inquilino", ignore = true)
    @Mapping(target = "propiedad", ignore = true)
    @Mapping(target = "tipoAjuste", ignore = true)
    Contrato toEntity(ContratoRequestDTO requestDTO);
    
    @Mapping(target = "idContrato", ignore = true)
    @Mapping(target = "inquilino", ignore = true)
    @Mapping(target = "propiedad", ignore = true)
    @Mapping(target = "tipoAjuste", ignore = true)
    void updateEntityFromDTO(ContratoRequestDTO requestDTO, @MappingTarget Contrato contrato);
}
