package com.tecnologiainventario.api_inventario.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void enviarCorreo(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nicolesancho403@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }

}
