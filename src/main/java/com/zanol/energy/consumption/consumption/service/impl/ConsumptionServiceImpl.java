package com.zanol.energy.consumption.consumption.service.impl;

import com.zanol.energy.consumption.consumption.model.Consumption;
import com.zanol.energy.consumption.consumption.repository.ConsumptionRepository;
import com.zanol.energy.consumption.consumption.service.ConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {

    @Autowired
    ConsumptionRepository consumptionRepository;

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
        return Optional.ofNullable(Objects.isNull(consumption.getId())
                ? consumptionRepository.save(consumption)
                : null
        );
    }

    @Override
    public Optional<Consumption> update(Long id, Consumption consumption) {
        Optional<Consumption> consumptionData = consumptionRepository.findById(id);

        return Optional.ofNullable(consumptionData.isPresent()
                ? consumptionRepository.save(consumption)
                : null
        );
    }

    @Override
    public boolean delete(Long id) {
        boolean success = false;

        consumptionRepository.deleteById(id);
        Optional<Consumption> consumptionData = consumptionRepository.findById(id);

        if (consumptionData.isPresent()) {
            consumptionRepository.deleteById(id);
            success = true;
        }

        return success;
    }
}