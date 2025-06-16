package com.wisata.prod.controller;

import com.wisata.prod.entity.Favourite;
import com.wisata.prod.service.FavouriteService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/favourites")
public class FavouriteController {

    private final FavouriteService favouriteService;

    @PostMapping
    public ResponseEntity<Favourite> addFavourite(@RequestParam Long userId, @RequestParam Long destinationId) {
        Favourite favourite = favouriteService.addFavourite(userId, destinationId);
        return new ResponseEntity<>(favourite, HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<Favourite>> getFavouritesByUserId(@PathVariable Long userId) {
        List<Favourite> favourites = favouriteService.getFavouritesByUserId(userId);
        return new ResponseEntity<>(favourites, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> removeFavourite(@RequestParam Long userId, @RequestParam Long destinationId) {
        favouriteService.removeFavourite(userId, destinationId);
        return new ResponseEntity<>("Favourite removed successfully!", HttpStatus.OK);
    }
}