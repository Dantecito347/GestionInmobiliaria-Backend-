package com.utn.gestioninmobiliaria.service;

import com.utn.gestioninmobiliaria.dto.PersonaRequestDTO;
import com.utn.gestioninmobiliaria.dto.PersonaResponseDTO;
import com.utn.gestioninmobiliaria.entity.Persona;
import com.utn.gestioninmobiliaria.entity.TipoDocumento;
import com.utn.gestioninmobiliaria.mapper.PersonaMapper;
import com.utn.gestioninmobiliaria.repository.PersonaRepository;
import com.utn.gestioninmobiliaria.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class PersonaService {
    
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    public PersonaService(PersonaRepository personaRepository, PersonaMapper personaMapper) {
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
    }

    public List<PersonaResponseDTO> obtenerTodas() {
        List<Persona> personas = personaRepository.findAll();
        return personaMapper.toResponseDTOList(personas);
    }

    public PersonaResponseDTO guardar(PersonaRequestDTO requestDTO) {
        if (personaRepository.existsByTipoDocumento_IdTipoDocAndNroDocumento(
                requestDTO.getIdTipoDoc(), requestDTO.getNroDocumento())) {
            throw new IllegalArgumentException("Ya existe una persona registrada con el tipo y número de documento ingresado.");
        }
        Persona persona = personaMapper.toEntity(requestDTO);
        
        TipoDocumento td = new TipoDocumento();
        td.setIdTipoDoc(requestDTO.getIdTipoDoc());
        persona.setTipoDocumento(td);
        
        Persona personaGuardada = personaRepository.save(persona);
        return personaMapper.toResponseDTO(personaGuardada);
    }

    public PersonaResponseDTO actualizar(Long id, PersonaRequestDTO requestDTO) {
            Persona personaExistente = personaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));

            if (personaRepository.existsByTipoDocumento_IdTipoDocAndNroDocumentoAndIdPersonaNot(
                requestDTO.getIdTipoDoc(), requestDTO.getNroDocumento(), id)) {
            throw new IllegalArgumentException("Ya existe otra persona registrada con el mismo tipo y número de documento.");
            }

            personaMapper.updateEntityFromDto(requestDTO, personaExistente);

            TipoDocumento td = new TipoDocumento();
            td.setIdTipoDoc(requestDTO.getIdTipoDoc());
            personaExistente.setTipoDocumento(td);

        Persona personaActualizada = personaRepository.save(personaExistente);
        return personaMapper.toResponseDTO(personaActualizada);
    }

    public void eliminar(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new RuntimeException("Persona no encontrada con ID: " + id);
        }
        personaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PersonaResponseDTO> buscarSugerencias(String termino) {
        if (termino == null || termino.trim().length() < 2) {
            return Collections.emptyList();
        }

        Pageable limite = PageRequest.of(0, 10);
        List<Persona> personas = personaRepository.buscarSugerencias(termino.trim(), limite);
        return personaMapper.toResponseDTOList(personas);
    }
}
