package com.utn.gestioninmobiliaria.service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarCorreoRecuperacion(String destinatario, String token) {
        String urlRestablecimiento = "http://localhost:5173/reset-password?token=" + token;

        String contenidoHtml = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
            </head>
            <body style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f1f5f9; margin: 0; padding: 40px 10px;">
                <div style="max-width: 550px; margin: 0 auto; background-color: #ffffff; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 15px rgba(0,0,0,0.05); border: 1px solid #e2e8f0;">
                    
                    <!-- Encabezado -->
                    <div style="background-color: #2563eb; padding: 25px 30px; text-align: center;">
                        <h1 style="color: #ffffff; margin: 0; font-size: 22px; font-weight: 600; letter-spacing: 0.5px;">Gestión Inmobiliaria</h1>
                    </div>
                    
                    <!-- Cuerpos de texto -->
                    <div style="padding: 30px; color: #334155;">
                        <h2 style="color: #0f172a; margin-top: 0; font-size: 20px;">Restablecer contraseña</h2>
                        <p style="font-size: 15px; line-height: 1.6; color: #475569;">
                            Hola, recibimos una solicitud para restablecer la contraseña asociada a tu cuenta.
                        </p>
                        <p style="font-size: 15px; line-height: 1.6; color: #475569;">
                            Haz clic en el siguiente botón para continuar con el proceso e ingresar tu nueva clave:
                        </p>
                        
                        <!-- Botón Principal -->
                        <div style="text-align: center; margin: 32px 0;">
                            <a href="%s" style="background-color: #2563eb; color: #ffffff; padding: 14px 28px; text-decoration: none; border-radius: 8px; font-weight: 600; font-size: 15px; display: inline-block; box-shadow: 0 2px 5px rgba(37, 99, 235, 0.3);">Restablecer Contraseña</a>
                        </div>

                        <!-- Bloque de advertencia -->
                        <div style="background-color: #f8fafc; padding: 14px 16px; border-radius: 8px; border-left: 4px solid #2563eb; font-size: 13px; color: #64748b; line-height: 1.5;">
                            ⏱️ <strong>Nota:</strong> Este enlace expira en <strong>15 minutos</strong>.<br>
                            Si no solicitaste este cambio, puedes ignorar este mensaje de forma segura.
                        </div>
                    </div>

                    <!-- Pie de página -->
                    <div style="background-color: #f8fafc; padding: 18px 30px; text-align: center; border-top: 1px solid #e2e8f0;">
                        <p style="margin: 0; font-size: 12px; color: #94a3b8;">
                            © Gestión Inmobiliaria UTN. Todos los derechos reservados.
                        </p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(urlRestablecimiento);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setSubject("Restablecer Contraseña - Gestión Inmobiliaria");
            helper.setText(contenidoHtml, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo de recuperación: " + e.getMessage());
        }
    }
}
