package com.utn.gestioninmobiliaria.service;
import com.utn.gestioninmobiliaria.dto.TipoInmuebleDTO;
import com.utn.gestioninmobiliaria.repository.TipoInmuebleRepository;
import com.utn.gestioninmobiliaria.entity.TipoInmueble;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TipoInmuebleService {
    private final TipoInmuebleRepository tipoInmuebleRepository;

    public TipoInmuebleService(TipoInmuebleRepository tipoInmuebleRepository){
        this.tipoInmuebleRepository = tipoInmuebleRepository;
    }

    public List<TipoInmuebleDTO> obtenerTodos(){
        List<TipoInmueble> tipos = tipoInmuebleRepository.findAll();
        List<TipoInmuebleDTO> dtos = new ArrayList();

        for(TipoInmueble t: tipos){
            TipoInmuebleDTO dto = new TipoInmuebleDTO();
            dto.setIdTipo(t.getIdTipo());
            dto.setDescripcion(t.getDescripcion());
            dtos.add(dto);
        }
        return dtos;
    }
}
