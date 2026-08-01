package com.utn.gestioninmobiliaria.service;
import com.utn.gestioninmobiliaria.dto.ContratoRequestDTO;
import com.utn.gestioninmobiliaria.dto.ContratoResponseDTO;
import com.utn.gestioninmobiliaria.repository.ContratoRepository;
import com.utn.gestioninmobiliaria.mapper.ContratoMapper;
import com.utn.gestioninmobiliaria.entity.Contrato;
import com.utn.gestioninmobiliaria.repository.PersonaRepository;
import com.utn.gestioninmobiliaria.entity.Persona;
import com.utn.gestioninmobiliaria.repository.PropiedadRepository;
import com.utn.gestioninmobiliaria.entity.Propiedad;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;
    private final PersonaRepository personaRepository;
    private final PropiedadRepository propiedadRepository;
    private final ContratoMapper contratoMapper;

    public ContratoService(ContratoRepository contratoRepository, PersonaRepository personaRepository, PropiedadRepository propiedadRepository, ContratoMapper contratoMapper){
        this.contratoRepository = contratoRepository;
        this.personaRepository = personaRepository;
        this.propiedadRepository = propiedadRepository;
        this.contratoMapper = contratoMapper;
    }

    public List<ContratoResponseDTO> obtenerTodos(){
        List<Contrato> contratos = contratoRepository.findAll();
        return contratoMapper.toResponseDTOList(contratos);
    }

    public ContratoResponseDTO guardar(ContratoRequestDTO requestDTO){
        Contrato contrato = contratoMapper.toEntity(requestDTO);
        Persona inquilino = personaRepository.findById(requestDTO.getIdInquilino())
                            .orElseThrow(() -> new IllegalArgumentException("Inquilino no encontrado con ID: " + requestDTO.getIdInquilino()));

        Propiedad propiedad = propiedadRepository.findById(requestDTO.getIdPropiedad())
                              .orElseThrow(() -> new IllegalArgumentException("Propiedad no encontrada con ID: " + requestDTO.getIdPropiedad()));
        
        contrato.setInquilino(inquilino);
        contrato.setPropiedad(propiedad);
        
        Contrato contratoGuardado = contratoRepository.save(contrato);
        return contratoMapper.toResponseDTO(contratoGuardado);

    }

}
