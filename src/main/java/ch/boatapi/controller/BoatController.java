package ch.boatapi.controller;

import ch.boatapi.exception.ResourceNotFoundException;
import ch.boatapi.repository.boat.Boat;
import ch.boatapi.repository.boat.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BoatController {

    @Autowired
    private BoatRepository boatRepository;

    @GetMapping("/boats")
    public List<Boat> getAllBoats(){
        return (List<Boat>) boatRepository.findAll();
    }

    @GetMapping("/boats/{id}")
    public ResponseEntity<Boat> getBoatById(@PathVariable(value = "id") Long boatId) throws ResourceNotFoundException {
        Boat boat = boatRepository.findById(boatId).orElseThrow(() -> new ResourceNotFoundException("Boat not found with id: " + boatId));
        return ResponseEntity.ok().body(boat);
    }
}
