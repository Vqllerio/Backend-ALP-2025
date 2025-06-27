package com.wisata.prod.controller;

import lombok.AllArgsConstructor;
import com.wisata.prod.entity.Destination;
import com.wisata.prod.entity.dto.DestinationWithHistoryDTO;
import com.wisata.prod.service.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/destinations")
public class DestinationController {
    
    private final DestinationService destinationService;

    @PostMapping
    // http://localhost:8080/api/destinations
    // This endpoint creates a new destination
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination){
        Destination savedDestination = destinationService.createDestination(destination);
        return new ResponseEntity<>(savedDestination, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    // Example: http://localhost:8080/api/destinations/1
    public ResponseEntity<Destination> getDestinationById(@PathVariable("id") Integer destinationId){
        Destination destination = destinationService.getDestinationById(destinationId);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    @GetMapping
    // Example: http://localhost:8080/api/destinations
    public ResponseEntity<List<Destination>> getAllDestinations(){
        List<Destination> destinations = destinationService.getAllDestinations();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @PutMapping("{id}")
    // http://localhost:8080/api/Destinations/1
    public ResponseEntity<Destination> updateDestination(@PathVariable("id") Integer destinationId,
                                           @RequestBody Destination destination){
        destination.setIdDestination(destinationId);
        Destination updatedDestination = destinationService.updateDestination(destination);
        return new ResponseEntity<>(updatedDestination, HttpStatus.OK);
    }

    @PostMapping("/{id}/rating")
    // Example: http://localhost:8080/api/destinations/1/rating?rating=5
    // Endpoint for rating submission
    public ResponseEntity<Destination> submitRating(@PathVariable Integer id, @RequestParam BigDecimal rating) {
        Destination updated = destinationService.submitRating(id, rating);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDestination(@PathVariable("id") Integer destinationId){
        destinationService.deleteDestination(destinationId);
        return new ResponseEntity<>("Destination successfully deleted!", HttpStatus.OK);
    }

    // Get destinations by category
    @GetMapping(params = "category")
    public ResponseEntity<List<Destination>> getByCategory(@RequestParam String category) {
        List<Destination> destinations = destinationService.getAllDestinations().stream()
            .filter(dest -> dest.getCategory() != null && dest.getCategory().toLowerCase().contains(category.toLowerCase()))
            .toList();
        return ResponseEntity.ok(destinations);
    }

    // Search destinations by location
    @GetMapping(params = "location")
    public ResponseEntity<List<Destination>> getByLocation(@RequestParam String location) {
        List<Destination> destinations = destinationService.getAllDestinations().stream()
            .filter(dest -> dest.getLocation() != null && dest.getLocation().toLowerCase().contains(location.toLowerCase()))
            .toList();
        return ResponseEntity.ok(destinations);
    }

    // Get destination with full history data
    @GetMapping("/{id}/full")
    public ResponseEntity<DestinationWithHistoryDTO> getDestinationWithHistory(@PathVariable Integer id) {
        return ResponseEntity.ok(destinationService.getDestinationWithHistory(id));
    }

    // Get only history data
    @GetMapping("/{id}/history")
    public ResponseEntity<Map<String, String>> getHistoryData(@PathVariable Integer id) {
        DestinationWithHistoryDTO dto = destinationService.getDestinationWithHistory(id);
        Map<String, String> history = Map.of(
                "title", dto.getHistoryTitle() != null ? dto.getHistoryTitle() : "",
                "content", dto.getHistoryContent() != null ? dto.getHistoryContent() : "",
                "image", dto.getHistoryImage() != null ? dto.getHistoryImage() : "",
                "location", dto.getLocation() != null ? dto.getLocation() : ""
        );
        return ResponseEntity.ok(history);
    }

    // Update history data
    @PutMapping("/{id}/history")
    public ResponseEntity<Destination> updateHistory(
            @PathVariable Integer id,
            @RequestBody Map<String, String> historyData) {

        Destination destination = destinationService.getDestinationById(id);
        destination.setHistoryTitle(historyData.get("title"));
        destination.setHistoryContent(historyData.get("content"));
        destination.setHistoryImage(historyData.get("image"));

        Destination updated = destinationService.updateDestination(destination);
        return ResponseEntity.ok(updated);
    }

    // Update comprehensive data
    @PutMapping("/{id}/full")
    public ResponseEntity<DestinationWithHistoryDTO> updateFullDestination(
            @PathVariable Integer id,
            @RequestBody DestinationWithHistoryDTO dto) {

        return ResponseEntity.ok(destinationService.updateDestinationWithHistory(id, dto));
    }
}
