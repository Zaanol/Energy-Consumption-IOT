package com.zanol.energy.consumption.consumption.controller;

import com.zanol.energy.consumption.consumption.model.System;
import com.zanol.energy.consumption.consumption.service.SystemService;
import com.zanol.energy.consumption.utils.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    SystemService systemService;

    @GetMapping("")
    public ResponseEntity<List<System>> getAll() {
        return new ResponseEntity<>(systemService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<System> getById(@PathVariable("id") Long id) {
        return systemService.getById(id).map(sytem -> new ResponseEntity<>(sytem, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/current")
    public ResponseEntity<System> getById() {
        return systemService.getCurrentState().map(sytem -> new ResponseEntity<>(sytem, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<System> create(@RequestBody System system) {
        return systemService.create(system)
                .map(created -> {
                    if (!created.getOnline())
                        systemService.sendMail(EmailTemplate.getSystemOffTemplate("willianzanoll@hotmail.com", system.getMaxCurrent(), system.getLogReading()));
                    return new ResponseEntity<>(created, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
    }
}