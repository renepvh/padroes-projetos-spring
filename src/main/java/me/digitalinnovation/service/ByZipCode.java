package me.digitalinnovation.service;

import me.digitalinnovation.model.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ByZipCode {
    @GetMapping("/{zipCode}/json/")
    Address consultZipCode(@PathVariable("zipCode") String zipCode);
}
