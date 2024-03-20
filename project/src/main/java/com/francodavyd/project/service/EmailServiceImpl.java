package com.francodavyd.project.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements IEmailService{
    @Value("${mail.username}")
    private String mailUsername;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void enviar(String destinatario, String asunto, String mensaje, File pdf) {
       try {
           MimeMessage mimeMessage = mailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true, StandardCharsets.UTF_8.name());
           mimeMessageHelper.setFrom(mailUsername);
           mimeMessageHelper.setTo(destinatario);
           mimeMessageHelper.setSubject(asunto);
           mimeMessageHelper.setText(mensaje);
           mimeMessageHelper.addAttachment(pdf.getName(), pdf);

           mailSender.send(mimeMessage);

       } catch (MessagingException e){
         e.printStackTrace();
       }


    }
}
