package com.wisata.prod.controller;

import com.wisata.prod.entity.Favourites;
import com.wisata.prod.repository.FavouriteRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteRepository favouriteRepository;

    // Get all favorites for a user
    @GetMapping
    public List<Favourites> getUserFavorites(@RequestParam Integer userId) {
        return favouriteRepository.findByIdUser(userId);
    }

    // Check if a destination is favorited by user
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkFavorite(
            @RequestParam Integer userId,
            @RequestParam Integer destinationId) {
        boolean exists = favouriteRepository.existsByIdUserAndIdDestination(userId, destinationId);
        return ResponseEntity.ok(exists);
    }

    // Toggle favorite status
    @PostMapping
    public ResponseEntity<?> toggleFavorite(
            @RequestParam Integer userId,
            @RequestParam Integer destinationId) {

        // Check if already favorited
        if (favouriteRepository.existsByIdUserAndIdDestination(userId, destinationId)) {
            favouriteRepository.deleteByIdUserAndIdDestination(userId, destinationId);
            return ResponseEntity.ok().body(Map.of("isFavorite", false));
        } else {
            Favourites newFavorite = new Favourites();
            newFavorite.setIdUser(userId);
            newFavorite.setIdDestination(destinationId);
            favouriteRepository.save(newFavorite);
            return ResponseEntity.ok().body(Map.of("isFavorite", true));
        }
    }
}