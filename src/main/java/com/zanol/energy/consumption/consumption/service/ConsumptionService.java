package com.zanol.energy.consumption.consumption.service;

import com.zanol.energy.consumption.consumption.model.Consumption;

import java.util.List;
import java.util.Optional;

public interface ConsumptionService {

    List<Consumption> getAll();

    Optional<Consumption> getById(Long id);

    Optional<Consumption> create(Consumption consumption);

    Optional<Consumption> update(Long id, Consumption consumption);

    boolean delete(Long id);
}