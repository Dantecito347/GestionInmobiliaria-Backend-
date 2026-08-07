package com.utn.gestioninmobiliaria.controller;
import com.utn.gestioninmobiliaria.dto.PagoRequestDTO;
import com.utn.gestioninmobiliaria.dto.PagoResponseDTO;
import com.utn.gestioninmobiliaria.service.PagoService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public List<PagoResponseDTO> listarTodos() {
        return pagoService.obtenerTodos();
    }

    @PostMapping
    public PagoResponseDTO crearPago(@Valid @RequestBody PagoRequestDTO requestDTO) {
        return pagoService.guardar(requestDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> actualizarPago(@PathVariable Integer id, @Valid @RequestBody PagoRequestDTO requestDTO) {
        PagoResponseDTO pagoActualizado = pagoService.actualizar(id, requestDTO);
        return ResponseEntity.ok(pagoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Integer id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
