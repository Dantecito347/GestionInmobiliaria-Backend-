package com.utn.gestioninmobiliaria.service;
import com.utn.gestioninmobiliaria.dto.ObligacionRequestDTO;
import com.utn.gestioninmobiliaria.dto.ObligacionResponseDTO;
import com.utn.gestioninmobiliaria.entity.Contrato;
import com.utn.gestioninmobiliaria.entity.Obligacion;
import com.utn.gestioninmobiliaria.repository.ContratoRepository;
import com.utn.gestioninmobiliaria.repository.ObligacionRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObligacionService {
    private final ObligacionRepository obligacionRepository;
    private final ContratoRepository contratoRepository;

    public ObligacionService(ObligacionRepository obligacionRepository, ContratoRepository contratoRepository) {
        this.obligacionRepository = obligacionRepository;
        this.contratoRepository = contratoRepository;
    }

    public List<ObligacionResponseDTO> obtenerTodas() {
        return mapearAListaDTO(obligacionRepository.findAll());
    }

    public List<ObligacionResponseDTO> obtenerPorContrato(Integer idContrato) {
        return mapearAListaDTO(obligacionRepository.findByContratoIdContrato(idContrato));
    }

    public ObligacionResponseDTO crear(ObligacionRequestDTO request) {
        Contrato contrato = contratoRepository.findById(request.getIdContrato())
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado con ID: " + request.getIdContrato()));

        Obligacion nueva = new Obligacion();
        nueva.setContrato(contrato);
        nueva.setDescripcion(request.getDescripcion());
        nueva.setImporteReferencia(request.getImporteReferencia());
        nueva.setPagadoPorInquilino(request.getPagadoPorInquilino());

        Obligacion guardada = obligacionRepository.save(nueva);
        return mapearADTO(guardada);
    }

    public ObligacionResponseDTO actualizar(Integer id, ObligacionRequestDTO request) {
        Obligacion existente = obligacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obligación no encontrada con ID: " + id));

        Contrato contrato = contratoRepository.findById(request.getIdContrato())
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado con ID: " + request.getIdContrato()));

        existente.setContrato(contrato);
        existente.setDescripcion(request.getDescripcion());
        existente.setImporteReferencia(request.getImporteReferencia());
        existente.setPagadoPorInquilino(request.getPagadoPorInquilino());

        Obligacion actualizada = obligacionRepository.save(existente);
        return mapearADTO(actualizada);
    }

    public void eliminar(Integer id) {
        if (!obligacionRepository.existsById(id)) {
            throw new RuntimeException("Obligación no encontrada con ID: " + id);
        }
        obligacionRepository.deleteById(id);
    }

    private ObligacionResponseDTO mapearADTO(Obligacion o) {
        ObligacionResponseDTO dto = new ObligacionResponseDTO();
        dto.setIdObligacion(o.getIdObligacion());
        dto.setIdContrato(o.getContrato().getIdContrato());
        dto.setDescripcion(o.getDescripcion());
        dto.setImporteReferencia(o.getImporteReferencia());
        dto.setPagadoPorInquilino(o.getPagadoPorInquilino());
        return dto;
    }

    private List<ObligacionResponseDTO> mapearAListaDTO(List<Obligacion> lista) {
        List<ObligacionResponseDTO> dtos = new ArrayList<>();
        for (Obligacion o : lista) {
            dtos.add(mapearADTO(o));
        }
        return dtos;
    }
}
