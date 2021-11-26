package com.zanol.energy.consumption.consumption.repository;

import com.zanol.energy.consumption.consumption.model.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SystemRepository extends JpaRepository<System, Long>, JpaSpecificationExecutor<System> {
    Optional<System> findTopByOrderByIdDesc();
}