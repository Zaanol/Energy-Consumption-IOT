package com.zanol.energy.consumption.consumption.controller;

import com.zanol.energy.consumption.consumption.model.Consumption;
import com.zanol.energy.consumption.consumption.model.Cost;
import com.zanol.energy.consumption.consumption.service.ConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumption")
public class ConsumptionController {

    @Autowired
    ConsumptionService consumptionService;

    @GetMapping("")
    public ResponseEntity<List<Consumption>> getAll() {
        return new ResponseEntity<>(consumptionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consumption> getById(@PathVariable("id") Long id) {
        return consumptionService.getById(id).map(consumption -> new ResponseEntity<>(consumption, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Consumption> create(@RequestBody Consumption consumption) {
        return consumptionService.create(consumption)
                .map(created -> new ResponseEntity<>(created, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consumption> update(@PathVariable("id") Long id, @RequestBody Consumption consumption) {
        return consumptionService.update(id, consumption)
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Consumption> deleteUser(@PathVariable("id") Long id) {
        return consumptionService.delete(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/calculate")
    public ResponseEntity<Cost> calculateCost() {
        return new ResponseEntity<>(consumptionService.calculateCost(), HttpStatus.OK);
    }
}