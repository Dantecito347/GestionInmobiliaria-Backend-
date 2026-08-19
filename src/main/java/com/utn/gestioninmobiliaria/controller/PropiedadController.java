package com.utn.gestioninmobiliaria.controller;
import com.utn.gestioninmobiliaria.dto.PropiedadRequestDTO;
import com.utn.gestioninmobiliaria.dto.PropiedadResponseDTO;
import com.utn.gestioninmobiliaria.service.PropiedadService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {
    private final PropiedadService propiedadService;

    public PropiedadController(PropiedadService propiedadService) {
        this.propiedadService = propiedadService;
    }

    @GetMapping
    public List<PropiedadResponseDTO> listarTodas() {
        return propiedadService.obtenerTodas();
    }

    @PostMapping
    public PropiedadResponseDTO crearPropiedad(@Valid @RequestBody PropiedadRequestDTO requestDTO) {
        return propiedadService.guardar(requestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropiedadResponseDTO> actualizarPropiedad(@PathVariable Integer id, @Valid @RequestBody PropiedadRequestDTO requestDTO) {
        PropiedadResponseDTO propiedadActualizada = propiedadService.actualizar(id, requestDTO);
        return ResponseEntity.ok(propiedadActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPropiedad(@PathVariable Integer id) {
        propiedadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sugerencias")
    public List<PropiedadResponseDTO> buscarSugerencias(
            @RequestParam(name = "termino", defaultValue = "") String termino) {
        return propiedadService.buscarSugerencias(termino);
    }
}
