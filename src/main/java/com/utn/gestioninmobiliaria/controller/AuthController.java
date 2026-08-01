package com.utn.gestioninmobiliaria.controller;
import com.utn.gestioninmobiliaria.dto.LoginRequestDTO;
import com.utn.gestioninmobiliaria.dto.LoginResponseDTO;
import com.utn.gestioninmobiliaria.dto.RegisterRequestDTO;
import com.utn.gestioninmobiliaria.entity.Usuario;
import com.utn.gestioninmobiliaria.repository.UsuarioRepository;
import com.utn.gestioninmobiliaria.security.JwtUtil;
import com.utn.gestioninmobiliaria.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UsuarioRepository usuarioRepository, 
            JwtUtil jwtUtil, 
            AuthService authService,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        System.out.println(">>> [LOGIN] Usuario recibido: " + request.getUsername());
    System.out.println(">>> [LOGIN] Password recibido: " + request.getPassword());

    Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
            .orElse(null);

    System.out.println(">>> [LOGIN] ¿Usuario existe en BD?: " + (usuario != null));

    if (usuario != null) {
        System.out.println(">>> [LOGIN] Hash guardado en BD: " + usuario.getPasswordHash());
        System.out.println(">>> [LOGIN] ¿El usuario está activo?: " + usuario.getActivo());
    }

    if (usuario == null || !passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
        System.out.println(">>> [LOGIN] FALLÓ: Usuario no existe o la contraseña no coincide.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas");
    }

    if (!usuario.getActivo()) {
        System.out.println(">>> [LOGIN] FALLÓ: El usuario está desactivado.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario inactivo");
    }

    String nombrePerfil = usuario.getPerfil() != null ? usuario.getPerfil().getNombrePerfil() : "SIN_PERFIL";
    String token = jwtUtil.generarToken(usuario.getUsername(), nombrePerfil);

    System.out.println(">>> [LOGIN] ÉXITO: Login correcto para " + usuario.getUsername());
    return ResponseEntity.ok(new LoginResponseDTO(token, usuario.getUsername(), nombrePerfil));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        try {
            String respuesta = authService.registrarUsuario(request);
            return ResponseEntity.ok(Map.of("message", respuesta));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
