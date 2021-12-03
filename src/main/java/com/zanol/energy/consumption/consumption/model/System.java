package com.zanol.energy.consumption.consumption.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class System {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date = LocalDateTime.now();
    private Boolean online;
    private Long maxCurrent;
    private Long logReading;

    public System() {
    }

    public System(Long id, LocalDateTime date, Boolean online, Long maxCurrent, Long logReading) {
        this.id = id;
        this.date = date;
        this.online = online;
        this.maxCurrent = maxCurrent;
        this.logReading = logReading;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Long getMaxCurrent() {
        return maxCurrent;
    }

    public void setMaxCurrent(Long maxCurrent) {
        this.maxCurrent = maxCurrent;
    }

    public Long getLogReading() {
        return logReading;
    }

    public void setLogReading(Long logReading) {
        this.logReading = logReading;
    }
}