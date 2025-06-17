package com.wisata.prod.controller;

import lombok.AllArgsConstructor;
import com.wisata.prod.entity.Destination;
import com.wisata.prod.service.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/destinations")
public class DestinationController {
    
    private DestinationService destinationService;

    @PostMapping
    // http://localhost:8080/api/destinations
    // This endpoint creates a new destination
    public ResponseEntity<Destination> createDestination(@RequestBody Destination Destination){
        Destination savedDestination = destinationService.createDestination(Destination);
        return new ResponseEntity<>(savedDestination, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    // Example: http://localhost:8080/api/destinations/1
    public ResponseEntity<Destination> getDestinationById(@PathVariable("id") Long DestinationId){
        Destination Destination = destinationService.getDestinationById(DestinationId);
        return new ResponseEntity<>(Destination, HttpStatus.OK);
    }

    @GetMapping
    // Example: http://localhost:8080/api/destinations
    public ResponseEntity<List<Destination>> getAllDestinations(){
        List<Destination> Destinations = destinationService.getAllDestinations();
        return new ResponseEntity<>(Destinations, HttpStatus.OK);
    }

    @PutMapping("{id}")
    // http://localhost:8080/api/Destinations/1
    public ResponseEntity<Destination> updateDestination(@PathVariable("id") Long DestinationId,
                                           @RequestBody Destination Destination){
        Destination.setId(DestinationId);
        Destination updatedDestination = destinationService.updateDestination(Destination);
        return new ResponseEntity<>(updatedDestination, HttpStatus.OK);
    }

    @PostMapping("/{id}/rating")
    // Example: http://localhost:8080/api/destinations/1/rating?rating=5
    // Endpoint for rating submission
    public ResponseEntity<Destination> submitRating(@PathVariable Long id, @RequestParam int rating) {
    Destination updated = destinationService.submitRating(id, rating);
    return ResponseEntity.ok(updated);
}

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDestination(@PathVariable("id") Long DestinationId){
        destinationService.deleteDestination(DestinationId);
        return new ResponseEntity<>("Destination successfully deleted!", HttpStatus.OK);
    }
}
