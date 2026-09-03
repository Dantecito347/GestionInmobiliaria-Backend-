package com.utn.gestioninmobiliaria.controller;
import com.utn.gestioninmobiliaria.dto.PropiedadRequestDTO;
import com.utn.gestioninmobiliaria.dto.PropiedadResponseDTO;
import com.utn.gestioninmobiliaria.service.PropiedadService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PropiedadResponseDTO crearPropiedad(
            @Valid @RequestPart("propiedad") PropiedadRequestDTO requestDTO,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        return propiedadService.guardar(requestDTO, imagen);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PropiedadResponseDTO> actualizarPropiedad(
            @PathVariable Integer id,
            @Valid @RequestPart("propiedad") PropiedadRequestDTO requestDTO,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        PropiedadResponseDTO propiedadActualizada = propiedadService.actualizar(id, requestDTO, imagen);
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
