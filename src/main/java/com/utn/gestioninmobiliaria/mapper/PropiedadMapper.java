package com.utn.gestioninmobiliaria.mapper;
import com.utn.gestioninmobiliaria.dto.PropiedadRequestDTO;
import com.utn.gestioninmobiliaria.dto.PropiedadResponseDTO;
import com.utn.gestioninmobiliaria.entity.Propiedad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropiedadMapper {
@Mapping(source = "propietario.idPersona", target = "idPropietario")
    @Mapping(source = "propietario.nombre", target = "nombrePropietario")
    @Mapping(source = "propietario.apellido", target = "apellidoPropietario")
    
    @Mapping(source = "tipoInmueble.idTipo", target = "idTipo")
    @Mapping(source = "tipoInmueble.descripcion", target = "tipoDescripcion")
    
    @Mapping(source = "zonas.idZona", target = "idZona")
    @Mapping(source = "zonas.nombreBarrio", target = "nombreBarrio")
    @Mapping(source = "zonas.zona", target = "zona")
    PropiedadResponseDTO toResponseDTO(Propiedad propiedad);

    java.util.List<PropiedadResponseDTO> toResponseDTOList(java.util.List<Propiedad> propiedades);

    @Mapping(target = "idPropiedad", ignore = true)
    @Mapping(target = "propietario", ignore = true)
    @Mapping(target = "tipoInmueble", ignore = true)
    @Mapping(target = "zonas", ignore = true)
    Propiedad toEntity(PropiedadRequestDTO requestDTO);
}
