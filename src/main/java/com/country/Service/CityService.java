package com.country.Service;

import com.country.DTO.CityDTO;
import com.country.Entity.City;
import com.country.Entity.Country;
import com.country.Entity.State;
import com.country.Repository.CityRepository;
import com.country.Repository.CountryRepository;
import com.country.Repository.StateRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;

    public CityService(CityRepository cityRepository, CountryRepository countryRepository, StateRepository stateRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
    }

    public CityDTO addCity(CityDTO city) {
        Country country = countryRepository.findByName(city.getCountry());
        State state = stateRepository.findByName(city.getState()); // Find the state by name

        City cityEntity = new City();
        cityEntity.setName(city.getName());
        cityEntity.setCode(city.getCode());
        cityEntity.setPopulation(city.getPopulation());
        cityEntity.setState(state);
        cityRepository.save(cityEntity);
        city.setId(cityEntity.getId());
        return city;
    }


    public List<CityDTO> getAllCities() {
        List<City> cities = cityRepository.findAll();

        return cities.stream().map(city -> {
            CityDTO dto = new CityDTO();
            dto.setId(city.getId());
            dto.setName(city.getName());
            dto.setCode(city.getCode());
            dto.setPopulation(city.getPopulation());
            dto.setCountry(city.getCountry() != null ? city.getCountry().getName() : "Unknown Country");
            dto.setState(city.getState() != null ? city.getState().getName() : "Unknown State");
            return dto;
        }).collect(Collectors.toList());
    }


    public CityDTO getCityById(long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with ID: " + id));
        CityDTO dto1 = new CityDTO();
        dto1.setId(city.getId());
        dto1.setName(city.getName());
        dto1.setCode(city.getCode());
        dto1.setPopulation(city.getPopulation());
        dto1.setCountry(city.getCountry() != null ? city.getCountry().getName() : "Unknown Country");
        dto1.setState(city.getState() != null ? city.getState().getName() : "Unknown State");
        return dto1;
    }

    @Transactional
    public CityDTO editCity(CityDTO cityDto, long id) {
        City city1 = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with ID: " + id));
        Country country = countryRepository.findByName(cityDto.getCountry());
        if (country == null) {
            throw new RuntimeException("Country not found with name: " + cityDto.getCountry());
        }
        State state = stateRepository.findByName(cityDto.getState());
        if (state == null) {
            throw new RuntimeException("State not found with name: " + cityDto.getState());
        }

        city1.setName(cityDto.getName());
        city1.setCode(cityDto.getCode());
        city1.setPopulation(cityDto.getPopulation());
        city1.setCountry(country);
        city1.setState(state);
        city1 = cityRepository.save(city1);
        cityDto.setId(city1.getId());
        return cityDto;
    }


    public boolean deleteCity(long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            return true;
        }
        return false;
    }

}