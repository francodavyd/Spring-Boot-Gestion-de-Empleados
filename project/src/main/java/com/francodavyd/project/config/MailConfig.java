package com.francodavyd.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Value("${mail.username}")
    private String mailUsername;
    @Value("${mail.password}")
    private String password;
    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername( mailUsername);
        mailSender.setPassword(password);

        Properties pr = mailSender.getJavaMailProperties();
        //Indicamos el protocolo que utilizaremos
        pr.put("mail.transport.protocol", "smtp");
        //Habilitamos autenticacion para smtp
        pr.put("mail.smtp.auth", "true");
        //Habilitamos el cifrado de la comunicacion
        pr.put("mail.smtp.starttls.enable", "true");
        //Imprimimos informacion en la consola
        //pr.put("mail.debug", "true");

        return mailSender;
    }
}
