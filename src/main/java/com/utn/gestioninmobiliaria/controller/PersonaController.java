package com.utn.gestioninmobiliaria.controller;

import com.utn.gestioninmobiliaria.dto.PersonaRequestDTO;
import com.utn.gestioninmobiliaria.dto.PersonaResponseDTO;
import com.utn.gestioninmobiliaria.service.PersonaService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    private PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public List<PersonaResponseDTO> listarPersonas() {
        return personaService.obtenerTodas();
    }

    @PostMapping
    public PersonaResponseDTO crearPersona(@Valid @RequestBody PersonaRequestDTO requestDTO) {
        return personaService.guardar(requestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponseDTO> actualizarPersona(
            @PathVariable Long id, 
            @Valid @RequestBody PersonaRequestDTO requestDTO) {
        return ResponseEntity.ok(personaService.actualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        personaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
