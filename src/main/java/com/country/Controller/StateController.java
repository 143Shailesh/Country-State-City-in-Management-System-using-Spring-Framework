package com.country.Controller;

import com.country.DTO.StateDTO;

import com.country.Service.StateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
@CrossOrigin(origins = "http://localhost:3000")
public class StateController {
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping
    public ResponseEntity<StateDTO> addState(@RequestBody StateDTO stateDto) {
        return ResponseEntity.ok(stateService.addState(stateDto));
    }
    @GetMapping
    public ResponseEntity<List<StateDTO>> getAllStates() {
        List<StateDTO> states = stateService.getAllStates();
        return ResponseEntity.ok(states);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDTO> editState(@PathVariable long id, @RequestBody StateDTO state) {
        return ResponseEntity.ok(stateService.editState(id, state));
    }

    @GetMapping("/{id}")
    public StateDTO getStateById(@PathVariable long id) {
        return stateService.getStateById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteState(@PathVariable long id) {
        return ResponseEntity.ok(stateService.deleteState(id));
    }

}
