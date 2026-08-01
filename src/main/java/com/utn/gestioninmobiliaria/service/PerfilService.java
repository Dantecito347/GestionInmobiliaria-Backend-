package com.utn.gestioninmobiliaria.service;
import com.utn.gestioninmobiliaria.dto.PerfilDTO;
import com.utn.gestioninmobiliaria.repository.PerfilRepository;
import com.utn.gestioninmobiliaria.entity.Perfil;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PerfilService {
    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository){
        this.perfilRepository = perfilRepository;
    }

    public List<PerfilDTO> obtenerTodos(){
        List<Perfil> perfiles = perfilRepository.findAll();
        List<PerfilDTO> dtos = new ArrayList<>();

        for(Perfil p: perfiles){
            PerfilDTO dto = new PerfilDTO();
            dto.setIdPerfil(p.getIdPerfil());
            dto.setNombrePerfil(p.getNombrePerfil());
            dtos.add(dto);
        }
        return dtos;
    }
}
