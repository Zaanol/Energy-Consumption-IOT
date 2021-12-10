package com.zanol.energy.consumption.consumption.model;

import java.math.BigDecimal;

public class Cost {
    BigDecimal hours;
    BigDecimal potency;
    BigDecimal cost;

    public Cost() {
    }

    public Cost(BigDecimal hours, BigDecimal potency, BigDecimal cost) {
        this.hours = hours;
        this.potency = potency;
        this.cost = cost;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public BigDecimal getPotency() {
        return potency;
    }

    public void setPotency(BigDecimal potency) {
        this.potency = potency;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}