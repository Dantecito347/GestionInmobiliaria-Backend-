package com.utn.gestioninmobiliaria.controller;
import com.utn.gestioninmobiliaria.dto.TipoAjusteDTO;
import com.utn.gestioninmobiliaria.service.TipoAjusteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-ajuste")
public class TipoAjusteController {
    private final TipoAjusteService tipoAjusteService;

    public TipoAjusteController(TipoAjusteService tipoAjusteService){
        this.tipoAjusteService = tipoAjusteService;
    }

    @GetMapping
    public List<TipoAjusteDTO> listarTodos(){
        return tipoAjusteService.obtenerTodos();
    }
}
