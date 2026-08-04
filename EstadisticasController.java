package com.utn.gestioninmobiliaria.controller;

import com.utn.gestioninmobiliaria.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estadisticas")
@CrossOrigin(origins = "*") // Ajusta esto a la URL de tu frontend en React
public class EstadisticasController {

    @Autowired
    private PagoRepository pagoRepository;

    @GetMapping("/ingresos-anuales")
    public ResponseEntity<List<Map<String, Object>>> getIngresosAnuales(
            @RequestParam(required = false) Integer anio) {
        
        if (anio == null) {
            anio = LocalDate.now().getYear();
        }

        List<Object[]> resultados = pagoRepository.obtenerIngresosPorMes(anio);
        List<Map<String, Object>> datosGrafico = new ArrayList<>();

        // Nombres de los meses para que quede guay en el gráfico
        String[] nombresMeses = {"", "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};

        for (Object[] fila : resultados) {
            Map<String, Object> dato = new HashMap<>();
            Integer mesNumero = (Integer) fila[0];
            dato.put("mes", nombresMeses[mesNumero]);
            dato.put("ingresos", fila[1]);
            datosGrafico.add(dato);
        }

        return ResponseEntity.ok(datosGrafico);
    }
}