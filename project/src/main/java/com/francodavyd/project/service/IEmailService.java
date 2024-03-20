package com.francodavyd.project.service;

import java.io.File;

public interface IEmailService {
    public void enviar(String destinatario, String asunto, String mensaje, File pdf);

}
