package com.utn.gestioninmobiliaria.controller;
import com.utn.gestioninmobiliaria.dto.PerfilDTO;
import com.utn.gestioninmobiliaria.service.PerfilService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilController {
    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService){
        this.perfilService = perfilService;
    }

    @GetMapping
    public List<PerfilDTO> listarTodos(){
        return perfilService.obtenerTodos();
    }
}
