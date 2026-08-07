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
import com.utn.gestioninmobiliaria.repository.TipoAjusteRepository;
import com.utn.gestioninmobiliaria.entity.TipoAjuste;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContratoService {
    private final ContratoRepository contratoRepository;
    private final PersonaRepository personaRepository;
    private final PropiedadRepository propiedadRepository;
    private final TipoAjusteRepository tipoAjusteRepository;
    private final ContratoMapper contratoMapper;

    public ContratoService(ContratoRepository contratoRepository, PersonaRepository personaRepository, PropiedadRepository propiedadRepository, TipoAjusteRepository tipoAjusteRepository, ContratoMapper contratoMapper){
        this.contratoRepository = contratoRepository;
        this.personaRepository = personaRepository;
        this.propiedadRepository = propiedadRepository;
        this.tipoAjusteRepository = tipoAjusteRepository;
        this.contratoMapper = contratoMapper;
    }

    public List<ContratoResponseDTO> obtenerTodos(){
        List<Contrato> contratos = contratoRepository.findAll();
        return contratoMapper.toResponseDTOList(contratos);
    }

    @Transactional 
    public ContratoResponseDTO guardar(ContratoRequestDTO requestDTO){
        if (requestDTO.getValorInicial() == null || requestDTO.getValorInicial().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El valor inicial del contrato debe ser mayor a 0");
        }

        if (requestDTO.getFechaFin().isBefore(requestDTO.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        if (contratoRepository.existsByPropiedadIdPropiedadAndEstadoIgnoreCase(requestDTO.getIdPropiedad(), "Activo")) {
            throw new IllegalArgumentException("La propiedad seleccionada ya tiene un contrato activo asignado");
        }

        Contrato contrato = contratoMapper.toEntity(requestDTO);
        
        Persona inquilino = personaRepository.findById(requestDTO.getIdInquilino())
                .orElseThrow(() -> new IllegalArgumentException("Inquilino no encontrado con ID: " + requestDTO.getIdInquilino()));
        Propiedad propiedad = propiedadRepository.findById(requestDTO.getIdPropiedad())
                .orElseThrow(() -> new IllegalArgumentException("Propiedad no encontrada con ID: " + requestDTO.getIdPropiedad()));
        TipoAjuste ajuste = tipoAjusteRepository.findById(requestDTO.getIdAjuste())
                .orElseThrow(() -> new RuntimeException("Tipo de ajuste no encontrado"));                  
        
        contrato.setInquilino(inquilino);
        contrato.setPropiedad(propiedad);
        contrato.setTipoAjuste(ajuste);

        Contrato contratoGuardado = contratoRepository.save(contrato);
        return contratoMapper.toResponseDTO(contratoGuardado);
    }

    @Transactional 
    public ContratoResponseDTO actualizar(Integer id, ContratoRequestDTO requestDTO) {
        Contrato contratoExistente = contratoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con ID: " + id));

        if (requestDTO.getValorInicial() == null || requestDTO.getValorInicial().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El valor inicial del contrato debe ser mayor a 0");
        }

        if (requestDTO.getFechaFin().isBefore(requestDTO.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        if (contratoRepository.existsByPropiedadIdPropiedadAndEstadoIgnoreCaseAndIdContratoNot(requestDTO.getIdPropiedad(), "Activo", id)) {
            throw new IllegalArgumentException("La propiedad seleccionada ya tiene otro contrato activo asignado");
        }
        
        if (contratoExistente.getObligaciones() != null) {
            contratoExistente.getObligaciones().clear();
        }

        contratoMapper.updateEntityFromDTO(requestDTO, contratoExistente);
        
        Persona inquilino = personaRepository.findById(requestDTO.getIdInquilino())
                .orElseThrow(() -> new IllegalArgumentException("Inquilino no encontrado con ID: " + requestDTO.getIdInquilino()));    
        Propiedad propiedad = propiedadRepository.findById(requestDTO.getIdPropiedad())
                .orElseThrow(() -> new IllegalArgumentException("Propiedad no encontrada con ID: " + requestDTO.getIdPropiedad()));
        TipoAjuste ajuste = tipoAjusteRepository.findById(requestDTO.getIdAjuste())
                .orElseThrow(() -> new RuntimeException("Tipo de ajuste no encontrado")); 

        contratoExistente.setInquilino(inquilino);
        contratoExistente.setPropiedad(propiedad);
        contratoExistente.setTipoAjuste(ajuste);
        
        Contrato contratoGuardado = contratoRepository.save(contratoExistente);
        return contratoMapper.toResponseDTO(contratoGuardado);
    }

    @Transactional 
    public void eliminar(Integer id) {
        if (!contratoRepository.existsById(id)) {
            throw new IllegalArgumentException("Contrato no encontrado con ID: " + id);
        }
        contratoRepository.deleteById(id);
    }

}
