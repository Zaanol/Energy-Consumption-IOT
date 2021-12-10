package com.zanol.energy.consumption.consumption.model;

import java.math.BigDecimal;

public class Cost {
    BigDecimal seconds;
    BigDecimal potency;
    BigDecimal cost;

    public Cost() {
    }

    public Cost(BigDecimal hours, BigDecimal potency, BigDecimal cost) {
        this.seconds = hours;
        this.potency = potency;
        this.cost = cost;
    }

    public BigDecimal getSeconds() {
        return seconds;
    }

    public void setSeconds(BigDecimal seconds) {
        this.seconds = seconds;
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