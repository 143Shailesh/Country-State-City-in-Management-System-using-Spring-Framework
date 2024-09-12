package com.country.Service;

import com.country.Entity.Country;
import com.country.Repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> getCountries() {


        return countryRepository.findAll();
    }

    public Country editCountry(Country country) {
        return countryRepository.save(country);
    }

    public boolean deleteCountry(int id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
