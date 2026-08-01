package com.utn.gestioninmobiliaria.mapper;
import com.utn.gestioninmobiliaria.dto.ZonaRequestDTO;
import com.utn.gestioninmobiliaria.dto.ZonaResponseDTO;
import com.utn.gestioninmobiliaria.entity.Zona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ZonaMapper {
    ZonaResponseDTO toResponseDTO(Zona zona);

    java.util.List<ZonaResponseDTO> toResponseDTOList(java.util.List<Zona> zonas);

    @Mapping(target = "idZona", ignore = true)
    Zona toEntity(ZonaRequestDTO requestDTO);
}
