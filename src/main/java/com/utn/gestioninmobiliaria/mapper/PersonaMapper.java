package com.utn.gestioninmobiliaria.mapper;

import com.utn.gestioninmobiliaria.dto.PersonaRequestDTO;
import com.utn.gestioninmobiliaria.dto.PersonaResponseDTO;
import com.utn.gestioninmobiliaria.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    @Mapping(source = "tipoDocumento.idTipoDoc", target = "idTipoDoc")
    @Mapping(source = "tipoDocumento.descripcion", target = "tipoDocumentoDescripcion")
    PersonaResponseDTO toResponseDTO(Persona persona);

    List<PersonaResponseDTO> toResponseDTOList(List<Persona> personas);

    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "fechaAltaSistema", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(source = "idTipoDoc", target = "tipoDocumento.idTipoDoc")
    Persona toEntity(PersonaRequestDTO requestDTO);

    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "fechaAltaSistema", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "tipoDocumento", ignore = true)
    void updateEntityFromDto(PersonaRequestDTO requestDTO, @MappingTarget Persona persona);
}
