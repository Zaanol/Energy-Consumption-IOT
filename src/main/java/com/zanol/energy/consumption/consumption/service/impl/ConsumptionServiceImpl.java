package com.zanol.energy.consumption.consumption.service.impl;

import com.zanol.energy.consumption.consumption.model.Consumption;
import com.zanol.energy.consumption.consumption.model.Cost;
import com.zanol.energy.consumption.consumption.model.System;
import com.zanol.energy.consumption.consumption.repository.ConsumptionRepository;
import com.zanol.energy.consumption.consumption.repository.SystemRepository;
import com.zanol.energy.consumption.consumption.service.ConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {

    @Autowired
    ConsumptionRepository consumptionRepository;

    @Autowired
    SystemRepository systemRepository;

    @Override
    public List<Consumption> getAll() {
        return consumptionRepository.findAll();
    }

    @Override
    public Optional<Consumption> getById(Long id) {
        return consumptionRepository.findById(id);
    }

    @Override
    public Optional<Consumption> create(Consumption consumption) {
        consumption.setHourlyCost(consumption.getChain().multiply(consumption.getVoltage()));

        return Optional.ofNullable(Objects.isNull(consumption.getId())
                ? consumptionRepository.save(consumption)
                : null
        );
    }

    @Override
    public Optional<Consumption> update(Long id, Consumption consumption) {
        Optional<Consumption> consumptionData = consumptionRepository.findById(id);

        if (consumptionData.isPresent()) {
            Consumption consData = consumptionData.get();

            Consumption cons = new Consumption();
            cons.setId(id);
            cons.setChain(Objects.isNull(consumption.getChain()) ? consData.getChain() : consumption.getChain());
            cons.setVoltage(Objects.isNull(consumption.getVoltage()) ? consData.getVoltage() : consumption.getVoltage());
            cons.setDate(consData.getDate());

            return Optional.of(consumptionRepository.save(cons));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Long id) {
        boolean success = false;

        Optional<Consumption> consumptionData = consumptionRepository.findById(id);

        if (consumptionData.isPresent()) {
            consumptionRepository.deleteById(id);
            success = true;
        }

        return success;
    }

    @Override
    public Cost calculateCost() {
        Optional<Consumption> opCon = consumptionRepository.findTopByOrderByIdDesc();

        Optional<System> opSys = systemRepository.findTopByOrderByIdDesc();

        if (opCon.isPresent() && opSys.isPresent()) {
            LocalDateTime startTime = LocalDateTime.now();
            LocalDateTime finishTime = LocalDateTime.now();

            Consumption consumption = opCon.get();
            System system = opSys.get();

            if (system.getOnline()) {
                startTime = system.getDate();
            } else {
                Optional<System> opSysOn = systemRepository.findById(system.getId() - 1);

                if (opSysOn.isPresent()) {
                    System systemOnline = opSysOn.get();

                    startTime = systemOnline.getDate();

                    if (systemOnline.getOnline())
                        throw new RuntimeException("Break in the log sequence (Online - Offline)");
                }

                finishTime = system.getDate();
            }

            BigDecimal potency = consumption.getHourlyCost().divide(new BigDecimal(1000));
            Long seconds = ChronoUnit.SECONDS.between(startTime, finishTime);

            BigDecimal hours = new BigDecimal(seconds / 60.00 / 60.00);

            BigDecimal cost = potency.multiply(hours).multiply(new BigDecimal("0.626867"));

            return new Cost(hours, potency, cost);
        } else {
            return null;
        }
    }
}