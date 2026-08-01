package com.utn.gestioninmobiliaria.service;
import com.utn.gestioninmobiliaria.dto.PagoRequestDTO;
import com.utn.gestioninmobiliaria.dto.PagoResponseDTO;
import com.utn.gestioninmobiliaria.repository.PagoRepository;
import com.utn.gestioninmobiliaria.mapper.PagoMapper;
import com.utn.gestioninmobiliaria.entity.Pago;
import com.utn.gestioninmobiliaria.repository.ContratoRepository;
import com.utn.gestioninmobiliaria.entity.Contrato;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PagoService {
    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;
    private final ContratoRepository contratoRepository;

    public PagoService(PagoRepository pagoRepository, PagoMapper pagoMapper, ContratoRepository contratoRepository){
        this.pagoRepository = pagoRepository;
        this.pagoMapper = pagoMapper;
        this.contratoRepository = contratoRepository;
    }

    public List<PagoResponseDTO> obtenerTodos(){
        List<Pago> pagos = pagoRepository.findAll();
        return pagoMapper.toResponseDTOList(pagos);
    }

    public PagoResponseDTO guardar(PagoRequestDTO requestDTO){
        Pago pago = pagoMapper.toEntity(requestDTO);
        Contrato contrato = contratoRepository.findById(requestDTO.getIdContrato())
                .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con ID: " + requestDTO.getIdContrato()));
        pago.setContrato(contrato);
        
        Pago pagoGuardado = pagoRepository.save(pago);
        return pagoMapper.toResponseDTO(pagoGuardado);
    }
}
