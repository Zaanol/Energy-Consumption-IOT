package com.zanol.energy.consumption.consumption.service;

import com.zanol.energy.consumption.consumption.model.System;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;
import java.util.Optional;

public interface SystemService {

    List<System> getAll();

    Optional<System> getById(Long id);

    Optional<System> getCurrentState();

    Optional<System> create(System system);

    void sendMail(SimpleMailMessage message);
}