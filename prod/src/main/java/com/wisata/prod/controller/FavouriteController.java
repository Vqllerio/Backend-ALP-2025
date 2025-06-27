// package com.wisata.prod.controller;

// import com.wisata.prod.entity.Favourites;
// import com.wisata.prod.service.FavouriteService;

// import lombok.AllArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @AllArgsConstructor
// @RequestMapping("api/favourites")
// public class FavouriteController {

//     private final FavouriteService favouriteService;

//     @PostMapping
//     // Endpoint to add a favourite destination for a user
//     // Example: http://localhost:8080/api/favourites?userId=1&destinationId=2
//     // Uses User ID and Destination ID as request parameters
//     public ResponseEntity<Favourites> addFavourite(@RequestParam Long userId, @RequestParam Long destinationId) {
//         Favourites favourite = favouriteService.addFavourite(userId, destinationId);
//         return new ResponseEntity<>(favourite, HttpStatus.CREATED);
//     }

//     @GetMapping("{userId}")
//     // TO get all favourites for a specific user
//     // Example: http://localhost:8080/api/favourites/1
//     // Uses User ID as a path variable
//     public ResponseEntity<List<Favourites>> getFavouritesByUserId(@PathVariable Long userId) {
//         List<Favourites> favourites = favouriteService.getFavouritesByUserId(userId);
//         return new ResponseEntity<>(favourites, HttpStatus.OK);
//     }

//     @DeleteMapping
//     public ResponseEntity<String> removeFavourite(@RequestParam Long userId, @RequestParam Long destinationId) {
//         favouriteService.removeFavourite(userId, destinationId);
//         return new ResponseEntity<>("Favourite removed successfully!", HttpStatus.OK);
//     }
// }