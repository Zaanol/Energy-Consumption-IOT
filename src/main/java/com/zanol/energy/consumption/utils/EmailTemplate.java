package com.zanol.energy.consumption.utils;

import org.springframework.mail.SimpleMailMessage;

public class EmailTemplate {
    public static SimpleMailMessage getSystemOffTemplate(String sendTo, Long maxCurrent, Long logReading) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("O dispositivo foi interrompido devido a pico de Corrente Elétrica registrado pelo sensor ACS712. " +
                "\nCorrente Máxima: " + maxCurrent + "\nLeitura Registrada: " + logReading);
        message.setSubject("Sistema Desligado");
        message.setTo(sendTo, "fernando.valle@catolicasc.edu.br");
        message.setFrom("iot.catolica@gmail.com");

        return message;
    }
}