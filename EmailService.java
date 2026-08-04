package com.utn.gestioninmobiliaria.service;

import com.utn.gestioninmobiliaria.entity.Pago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarComprobantePago(Pago pago) {
        SimpleMailMessage email = new SimpleMailMessage();
        
        // Asumiendo que la entidad Persona tiene el campo "email"
        String correoInquilino = pago.getContrato().getInquilino().getEmail(); 
        String nombreInquilino = pago.getContrato().getInquilino().getNombre();
        
        email.setTo(correoInquilino);
        email.setSubject("Confirmación de Pago - Gestión Inmobiliaria");
        email.setText("¡Hola " + nombreInquilino + "!\n\n" +
                "Hemos recibido correctamente tu pago de " + pago.getMontoPagado() + "€ " +
                "correspondiente al mes " + pago.getMesCobertura() + "/" + pago.getAnioCobertura() + ".\n\n" +
                "¡Muchísimas gracias!\n" +
                "Tu inmobiliaria de confianza.");
        
        mailSender.send(email);
    }
}