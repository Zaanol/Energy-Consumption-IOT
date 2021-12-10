package com.zanol.energy.consumption.consumption.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal voltage;
    private BigDecimal chain;
    private BigDecimal potency;
    private LocalDateTime date = LocalDateTime.now();

    public Consumption() {
    }

    public Consumption(Long id, BigDecimal voltage, BigDecimal chain, LocalDateTime date) {
        this.id = id;
        this.voltage = voltage;
        this.chain = chain;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }

    public BigDecimal getChain() {
        return chain;
    }

    public void setChain(BigDecimal chain) {
        this.chain = chain;
    }

    public BigDecimal getPotency() {
        return potency;
    }

    public void setPotency(BigDecimal potency) {
        this.potency = potency;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}