package com.country.Controller;

import com.country.DTO.CityDTO;
import com.country.Service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<CityDTO> addCity(@RequestBody CityDTO cityDto) {
        return ResponseEntity.ok(cityService.addCity(cityDto));
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        List<CityDTO> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    @PutMapping("/{id}")
    public ResponseEntity editCity(@PathVariable long id, @RequestBody CityDTO city) {
        return ResponseEntity.ok(cityService.editCity(city, id));
    }

    @GetMapping("/{id}")
    public CityDTO getCityById(@PathVariable long id) {
        return cityService.getCityById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCity(@PathVariable long id) {
        return ResponseEntity.ok(cityService.deleteCity(id));
    }
}