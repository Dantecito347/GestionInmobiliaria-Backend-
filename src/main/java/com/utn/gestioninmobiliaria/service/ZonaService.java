package com.utn.gestioninmobiliaria.service;
import com.utn.gestioninmobiliaria.dto.ZonaRequestDTO;
import com.utn.gestioninmobiliaria.dto.ZonaResponseDTO;
import com.utn.gestioninmobiliaria.entity.Zona;
import com.utn.gestioninmobiliaria.repository.ZonaRepository;
import com.utn.gestioninmobiliaria.mapper.ZonaMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ZonaService {
    private final ZonaRepository zonaRepository;
    private final ZonaMapper zonaMapper;

    public ZonaService(ZonaRepository zonaRepository, ZonaMapper zonaMapper){
        this.zonaRepository = zonaRepository;
        this.zonaMapper = zonaMapper;
    }

    public List<ZonaResponseDTO> obtenerTodas(){
        List<Zona> zonas = zonaRepository.findAll();
        return zonaMapper.toResponseDTOList(zonas);
    }

    public ZonaResponseDTO guardar(ZonaRequestDTO requestDTO){
        Zona zona = zonaMapper.toEntity(requestDTO);
        Zona zonaGuardada = zonaRepository.save(zona);
        return zonaMapper.toResponseDTO(zonaGuardada);
    }
}
