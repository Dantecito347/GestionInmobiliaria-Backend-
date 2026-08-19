package com.utn.gestioninmobiliaria.service;
import com.utn.gestioninmobiliaria.dto.PropiedadRequestDTO;
import com.utn.gestioninmobiliaria.dto.PropiedadResponseDTO;
import com.utn.gestioninmobiliaria.entity.Propiedad;
import com.utn.gestioninmobiliaria.entity.TipoInmueble;
import com.utn.gestioninmobiliaria.entity.Zona;
import com.utn.gestioninmobiliaria.entity.Persona;
import com.utn.gestioninmobiliaria.repository.PropiedadRepository;
import com.utn.gestioninmobiliaria.repository.TipoInmuebleRepository;
import com.utn.gestioninmobiliaria.repository.PersonaRepository;
import com.utn.gestioninmobiliaria.repository.ZonaRepository;
import com.utn.gestioninmobiliaria.mapper.PropiedadMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropiedadService {
    private final PropiedadRepository propiedadRepository;
    private final PersonaRepository personaRepository;
    private final TipoInmuebleRepository tipoInmuebleRepository;
    private final ZonaRepository zonaRepository;
    private final PropiedadMapper propiedadMapper;

    public PropiedadService(PropiedadRepository propiedadRepository, PersonaRepository personaRepository, PropiedadMapper propiedadMapper, TipoInmuebleRepository tipoInmuebleRepository, ZonaRepository zonaRepository) {
        this.propiedadRepository = propiedadRepository;
        this.personaRepository = personaRepository;
        this.propiedadMapper = propiedadMapper;
        this.tipoInmuebleRepository = tipoInmuebleRepository;
        this.zonaRepository = zonaRepository;
    }

    public List<PropiedadResponseDTO> obtenerTodas(){
        List<Propiedad> propiedades = propiedadRepository.findAll();
        return propiedadMapper.toResponseDTOList(propiedades);
    }

    public PropiedadResponseDTO guardar(PropiedadRequestDTO requestDTO){
        String direccionLimpia = requestDTO.getDireccion() != null ? requestDTO.getDireccion().trim() : "";

        if (propiedadRepository.existsByDireccionIgnoreCase(direccionLimpia)) {
            throw new IllegalArgumentException("Ya existe una propiedad registrada en la dirección: " + direccionLimpia);
        }

        Propiedad propiedad = propiedadMapper.toEntity(requestDTO);
        propiedad.setDireccion(direccionLimpia);
        Persona propietario = personaRepository.findById(requestDTO.getIdPropietario()) 
            .orElseThrow(() -> new RuntimeException("Propietario no encontrada con ID:" + requestDTO.getIdPropietario()));
        TipoInmueble tipo = tipoInmuebleRepository.findById(requestDTO.getIdTipo().intValue())
            .orElseThrow(() -> new RuntimeException("Tipo de Inmueble no encontrado con ID:" + requestDTO.getIdTipo()));
        propiedad.setTipoInmueble(tipo);
        Zona zona = zonaRepository.findById(requestDTO.getIdZona())
            .orElseThrow(() -> new RuntimeException("Zona no encontrada"));
        propiedad.setZonas(zona);

        propiedad.setPropietario(propietario);
        Propiedad propiedadGuardada = propiedadRepository.save(propiedad);
        return propiedadMapper.toResponseDTO(propiedadGuardada);
    }

    public PropiedadResponseDTO actualizar(Integer id, PropiedadRequestDTO requestDTO) {
        Propiedad propiedadExistente = propiedadRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con ID: " + id));
        String direccionLimpia = requestDTO.getDireccion() != null ? requestDTO.getDireccion().trim() : "";

        if (propiedadRepository.existsByDireccionIgnoreCaseAndIdPropiedadNot(direccionLimpia, id)) {
        throw new IllegalArgumentException("Ya existe otra propiedad registrada en la dirección: " + direccionLimpia);
        }

        propiedadExistente.setDireccion(direccionLimpia);
        propiedadExistente.setEstado(requestDTO.getEstado());
        propiedadExistente.setActivo(requestDTO.getActivo());

        Persona propietario = personaRepository.findById(requestDTO.getIdPropietario())
            .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        propiedadExistente.setPropietario(propietario);
        TipoInmueble tipo = tipoInmuebleRepository.findById(requestDTO.getIdTipo())
            .orElseThrow(() -> new RuntimeException("Tipo de Inmueble no encontrado"));
        propiedadExistente.setTipoInmueble(tipo);
        Zona zona = zonaRepository.findById(requestDTO.getIdZona())
            .orElseThrow(() -> new RuntimeException("Zona no encontrada"));
        propiedadExistente.setZonas(zona);

        Propiedad propiedadGuardada = propiedadRepository.save(propiedadExistente);
        return propiedadMapper.toResponseDTO(propiedadGuardada);
    }

    public void eliminar(Integer id) {
        if (!propiedadRepository.existsById(id)) {
            throw new RuntimeException("Propiedad no encontrada con ID: " + id);
        }
        propiedadRepository.deleteById(id);
    }

    public List<PropiedadResponseDTO> buscarSugerencias(String termino) {
    if (termino == null || termino.trim().isEmpty()) {
        return obtenerTodas();
    }
    List<Propiedad> propiedades = propiedadRepository.buscarSugerencias(termino.trim());
    return propiedadMapper.toResponseDTOList(propiedades);
    }
}
