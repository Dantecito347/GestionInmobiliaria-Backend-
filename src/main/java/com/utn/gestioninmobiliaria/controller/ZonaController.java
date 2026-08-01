package com.utn.gestioninmobiliaria.controller;
import com.utn.gestioninmobiliaria.dto.ZonaRequestDTO;
import com.utn.gestioninmobiliaria.dto.ZonaResponseDTO;
import com.utn.gestioninmobiliaria.service.ZonaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/zonas")
public class ZonaController {
    private final ZonaService zonaService;

    public ZonaController(ZonaService zonaService){
        this.zonaService = zonaService;
    }

    @GetMapping
    public List<ZonaResponseDTO> listarTodas(){
        return zonaService.obtenerTodas();
    }

    @PostMapping
    public ZonaResponseDTO crearZona(@Valid @RequestBody ZonaRequestDTO requestDTO){
        return zonaService.guardar(requestDTO);
    }
}
