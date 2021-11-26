package com.zanol.energy.consumption.consumption.service.impl;

import com.zanol.energy.consumption.consumption.model.System;
import com.zanol.energy.consumption.consumption.repository.SystemRepository;
import com.zanol.energy.consumption.consumption.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    SystemRepository systemRepository;

    @Override
    public List<System> getAll() {
        return systemRepository.findAll();
    }

    @Override
    public Optional<System> getById(Long id) {
        return systemRepository.findById(id);
    }

    @Override
    public Optional<System> getCurrentState() {
        return systemRepository.findTopByOrderByIdDesc();
    }

    @Override
    public Optional<System> create(System system) {
        return Optional.ofNullable(Objects.isNull(system.getId())
                ? systemRepository.save(system)
                : null
        );
    }
}