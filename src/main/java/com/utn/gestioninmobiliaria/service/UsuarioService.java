package com.utn.gestioninmobiliaria.service;
import com.utn.gestioninmobiliaria.dto.UsuarioDTO;
import com.utn.gestioninmobiliaria.entity.Usuario;
import com.utn.gestioninmobiliaria.repository.UsuarioRepository;
import com.utn.gestioninmobiliaria.repository.PerfilRepository;
import com.utn.gestioninmobiliaria.entity.Perfil;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
    }

    public List<UsuarioDTO> obtenerTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> dtos = new ArrayList<>();
        
        for (Usuario u : usuarios) {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setIdUsuario(u.getIdUsuario());
            dto.setUsername(u.getUsername());
            dto.setActivo(u.getActivo());
            if(u.getPerfil() != null){
                dto.setIdPerfil(u.getPerfil().getIdPerfil());
            }
            if (u.getPersona() != null) {
                dto.setIdPersona(u.getPersona().getIdPersona());
            }
            dtos.add(dto);
        }
        return dtos;
    }
}
