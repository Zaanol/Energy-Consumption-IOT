package com.zanol.energy.consumption.consumption.repository;

import com.zanol.energy.consumption.consumption.model.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long>, JpaSpecificationExecutor<Consumption> {
    Optional<Consumption> findTopByOrderByIdDesc();
}