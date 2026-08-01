package com.utn.gestioninmobiliaria.service;
import com.utn.gestioninmobiliaria.dto.TipoAjusteDTO;
import com.utn.gestioninmobiliaria.repository.TipoAjusteRepository;
import com.utn.gestioninmobiliaria.entity.TipoAjuste;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class TipoAjusteService {
    private final TipoAjusteRepository tipoAjusteRepository;

    public TipoAjusteService(TipoAjusteRepository tipoAjusteRepository){
        this.tipoAjusteRepository = tipoAjusteRepository;
    }

    public List<TipoAjusteDTO> obtenerTodos(){
        List<TipoAjuste> lista = tipoAjusteRepository.findAll();
        List<TipoAjusteDTO> dtos = new ArrayList<>();

        for(TipoAjuste ta: lista){
           TipoAjusteDTO dto = new TipoAjusteDTO();
           dto.setIdAjuste(ta.getIdAjuste());
           dto.setDescripcion(ta.getDescripcion());
           dtos.add(dto);
        }
        return dtos;
    }
}
