package com.utn.gestioninmobiliaria.controller;
import com.utn.gestioninmobiliaria.dto.ContratoRequestDTO;
import com.utn.gestioninmobiliaria.dto.ContratoResponseDTO;
import com.utn.gestioninmobiliaria.service.ContratoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {
    private final ContratoService contratoService;
    
    public ContratoController(ContratoService contratoService){
        this.contratoService = contratoService;
    }

    @GetMapping
    public List<ContratoResponseDTO> listarTodos(){
        return contratoService.obtenerTodos();
    }

    @PostMapping
    public ContratoResponseDTO crearContrato(@Valid @RequestBody ContratoRequestDTO requestDTO){
        return contratoService.guardar(requestDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> actualizarContrato(@PathVariable Integer id, @Valid @RequestBody ContratoRequestDTO requestDTO) {
        ContratoResponseDTO contratoActualizado = contratoService.actualizar(id, requestDTO);
        return ResponseEntity.ok(contratoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Integer id) {
        contratoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
