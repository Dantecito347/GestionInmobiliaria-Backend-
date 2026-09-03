package com.utn.gestioninmobiliaria.controller;
import com.utn.gestioninmobiliaria.entity.Notificacion;
import com.utn.gestioninmobiliaria.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionRepository notificacionRepository;

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Notificacion>> getNotificacionesUsuario(@PathVariable Integer idUsuario) {
        List<Notificacion> notificaciones = notificacionRepository.findNotificacionesPorUsuario(idUsuario);
        return ResponseEntity.ok(notificaciones);
    }

    @PutMapping("/{id}/leer")
    public ResponseEntity<?> marcarComoLeida(@PathVariable Integer id) {
        return notificacionRepository.findById(id).map(notificacion -> {
            notificacion.setLeida(true);
            notificacionRepository.save(notificacion);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Notificación leída correctamente");
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/usuario/{idUsuario}/leer-todas")
    public ResponseEntity<?> marcarTodasComoLeidas(@PathVariable Integer idUsuario) {
        notificacionRepository.marcarTodasComoLeidas(idUsuario);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Todas las notificaciones fueron marcadas como leídas");
        return ResponseEntity.ok(response);
    }
}
