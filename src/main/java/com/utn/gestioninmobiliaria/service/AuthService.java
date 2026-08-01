package com.utn.gestioninmobiliaria.service;
import com.utn.gestioninmobiliaria.dto.RegisterRequestDTO;
import com.utn.gestioninmobiliaria.entity.Perfil;
import com.utn.gestioninmobiliaria.entity.Persona;
import com.utn.gestioninmobiliaria.entity.TipoDocumento;
import com.utn.gestioninmobiliaria.entity.Usuario;
import com.utn.gestioninmobiliaria.mapper.AuthMapper;
import com.utn.gestioninmobiliaria.repository.PerfilRepository;
import com.utn.gestioninmobiliaria.repository.PersonaRepository;
import com.utn.gestioninmobiliaria.repository.TipoDocumentoRepository;
import com.utn.gestioninmobiliaria.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final PerfilRepository perfilRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PersonaRepository personaRepository,
            TipoDocumentoRepository tipoDocumentoRepository,
            PerfilRepository perfilRepository,
            AuthMapper authMapper,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.perfilRepository = perfilRepository;
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String registrarUsuario(RegisterRequestDTO request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está registrado");
        }

        TipoDocumento tipoDoc = tipoDocumentoRepository.findById(request.getIdTipoDoc())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));

        Perfil perfilDefault = perfilRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Perfil por defecto no encontrado"));

        Persona persona = authMapper.toPersona(request);
        persona.setTipoDocumento(tipoDoc);
        Persona personaGuardada = personaRepository.save(persona);

        Usuario usuario = authMapper.toUsuario(request);
        usuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        usuario.setPersona(personaGuardada);
        usuario.setPerfil(perfilDefault);
        usuarioRepository.save(usuario);

        return "Usuario registrado con éxito";
    }
}
