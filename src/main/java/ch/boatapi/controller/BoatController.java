package ch.boatapi.controller;

import ch.boatapi.exception.ResourceNotFoundException;
import ch.boatapi.model.BoatDto;
import ch.boatapi.repository.boat.Boat;
import ch.boatapi.repository.boat.BoatRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class BoatController {

    @Autowired
    private BoatRepository boatRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/boats")
    public List<Boat> getAllBoats() {
        return (List<Boat>) boatRepository.findAll();
    }

    @GetMapping("/boats/{id}")
    public ResponseEntity<Boat> getBoatById(@PathVariable(value = "id") UUID boatId) throws ResourceNotFoundException {
        Boat boat = boatRepository.findById(boatId).orElseThrow(() -> new ResourceNotFoundException("Boat not found with id: " + boatId));
        return ResponseEntity.ok().body(boat);
    }

    @PostMapping("/boats")
    public Boat createBoat(@Valid @RequestBody BoatDto boatDto) {
        Boat boat = mapper.map(boatDto, Boat.class);
        return this.boatRepository.save(boat);
    }

    @PutMapping("/boats/{id}")
    public Boat updateBoatById(@Valid @RequestBody BoatDto boatDto, @PathVariable(value = "id") UUID boatId) throws ResourceNotFoundException {
        if (boatRepository.existsById(boatId)) {
            Boat boat = mapper.map(boatDto, Boat.class);
            boat.setId(boatId);
            return this.boatRepository.save(boat);
        }
        throw new ResourceNotFoundException("Boat not found with id: " + boatId);
    }

    @DeleteMapping("/boats/{id}")
    public void deleteBoatById(@PathVariable(value = "id") UUID boatId) {
        this.boatRepository.deleteById(boatId);
    }
}
