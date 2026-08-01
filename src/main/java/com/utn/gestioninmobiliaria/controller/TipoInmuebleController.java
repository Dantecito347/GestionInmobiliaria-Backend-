package com.utn.gestioninmobiliaria.controller;
import com.utn.gestioninmobiliaria.dto.TipoInmuebleDTO;
import com.utn.gestioninmobiliaria.service.TipoInmuebleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-inmueble")
public class TipoInmuebleController {
    TipoInmuebleService tipoInmuebleService;

    public TipoInmuebleController(TipoInmuebleService tipoInmuebleService){
        this.tipoInmuebleService = tipoInmuebleService;
    }

    @GetMapping
    public List<TipoInmuebleDTO> listarTodos(){
        return tipoInmuebleService.obtenerTodos();
    }
}
