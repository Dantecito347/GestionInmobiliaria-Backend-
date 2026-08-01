package com.utn.gestioninmobiliaria.controller;
import com.utn.gestioninmobiliaria.dto.ObligacionRequestDTO;
import com.utn.gestioninmobiliaria.dto.ObligacionResponseDTO;
import com.utn.gestioninmobiliaria.service.ObligacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/obligaciones")
public class ObligacionController {
    private final ObligacionService obligacionService;

    public ObligacionController(ObligacionService obligacionService) {
        this.obligacionService = obligacionService;
    }

    @GetMapping
    public List<ObligacionResponseDTO> listarTodas() {
        return obligacionService.obtenerTodas();
    }

    @GetMapping("/contrato/{idContrato}")
    public List<ObligacionResponseDTO> listarPorContrato(@PathVariable Integer idContrato) {
        return obligacionService.obtenerPorContrato(idContrato);
    }

    @PostMapping
    public ResponseEntity<ObligacionResponseDTO> guardar(@RequestBody ObligacionRequestDTO request) {
        return new ResponseEntity<>(obligacionService.crear(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObligacionResponseDTO> modificar(@PathVariable Integer id, @RequestBody ObligacionRequestDTO request) {
        return ResponseEntity.ok(obligacionService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        obligacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
