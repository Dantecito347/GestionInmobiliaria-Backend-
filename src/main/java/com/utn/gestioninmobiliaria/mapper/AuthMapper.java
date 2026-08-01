package com.utn.gestioninmobiliaria.mapper;
import com.utn.gestioninmobiliaria.dto.RegisterRequestDTO;
import com.utn.gestioninmobiliaria.entity.Persona;
import com.utn.gestioninmobiliaria.entity.Usuario;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class AuthMapper {

    public Persona toPersona(RegisterRequestDTO request) {
        Persona persona = new Persona();
        persona.setNombre(request.getNombre());
        persona.setApellido(request.getApellido());
        persona.setNroDocumento(request.getNroDocumento());
        persona.setEmail(request.getEmail());
        persona.setTelefono(request.getTelefono());
        persona.setCuilCuit(request.getCuilCuit());
        persona.setCbuAlias(request.getCbuAlias());
        persona.setFechaAltaSistema(LocalDateTime.now());
        persona.setActivo(true);
        return persona;
    }

    public Usuario toUsuario(RegisterRequestDTO request) {
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setActivo(true);
        return usuario;
    }
}
