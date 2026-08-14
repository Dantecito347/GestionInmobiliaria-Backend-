package com.utn.gestioninmobiliaria.service;
import com.utn.gestioninmobiliaria.dto.UsuarioDTO;
import com.utn.gestioninmobiliaria.entity.Usuario;
import com.utn.gestioninmobiliaria.repository.UsuarioRepository;
import com.utn.gestioninmobiliaria.repository.PerfilRepository;
import com.utn.gestioninmobiliaria.entity.Perfil;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository,
                          EmailService emailService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
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

    public void solicitarRecuperacionPassword(String email) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            String token = UUID.randomUUID().toString();
            
            usuario.setResetToken(token);
            usuario.setResetTokenExpiration(LocalDateTime.now().plusMinutes(15));
            
            usuarioRepository.save(usuario);
            emailService.enviarCorreoRecuperacion(email, token);
        }
    }

    public void restablecerPassword(String token, String nuevaPassword) {
        Usuario usuario = usuarioRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("El token es inválido o no existe."));

        if (usuario.getResetTokenExpiration() == null || usuario.getResetTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("El enlace ha expirado. Por favor solicita uno nuevo.");
        }

        usuario.setPasswordHash(passwordEncoder.encode(nuevaPassword));
        usuario.setResetToken(null);
        usuario.setResetTokenExpiration(null);

        usuarioRepository.save(usuario);
    }
}
