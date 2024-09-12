package com.country.Service;

import com.country.DTO.StateDTO;
import com.country.Entity.Country;
import com.country.Entity.State;
import com.country.Repository.CountryRepository;
import com.country.Repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;

    public StateService(StateRepository stateRepository, CountryRepository countryRepository) {
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    public StateDTO addState(StateDTO stateDTO) {
        Country country = countryRepository.findByName(stateDTO.getCountry());
        State state = new State();
        state.setName(stateDTO.getName());
        state.setCode(stateDTO.getCode());
        state.setPopulation(stateDTO.getPopulation());
        state.setCountry(country);

        state = stateRepository.save(state);

        return convertToDTO(state);
    }

    public List<StateDTO> getAllStates() {
        List<State> states = stateRepository.findAll();
        return states.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StateDTO getStateById(long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));

        return convertToDTO(state);
    }

    public StateDTO editState(long id, StateDTO stateDTO) {
        State existingState = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));

        Country country = countryRepository.findByName(stateDTO.getCountry());

        existingState.setName(stateDTO.getName());
        existingState.setCode(stateDTO.getCode());
        existingState.setPopulation(stateDTO.getPopulation());
        existingState.setCountry(country);

        existingState = stateRepository.save(existingState);

        return convertToDTO(existingState);
    }

    public boolean deleteState(long id) {
        if (stateRepository.existsById(id)) {
            stateRepository.deleteById(id);
            return true;
        }
        return false;
    }


    private StateDTO convertToDTO(State state) {
        StateDTO dto = new StateDTO();
        dto.setId(state.getId());
        dto.setName(state.getName());
        dto.setCode(state.getCode());
        dto.setPopulation(state.getPopulation());
        dto.setCountry(state.getCountry() != null ? state.getCountry().getName() : "Unknown Country");
        return dto;
    }
}
