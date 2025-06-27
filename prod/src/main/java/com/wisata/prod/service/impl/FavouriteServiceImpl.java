package com.wisata.prod.service.impl;

import com.wisata.prod.entity.Favourites;
import com.wisata.prod.entity.User;
import com.wisata.prod.entity.Destination;
import com.wisata.prod.entity.FavouriteId;
import com.wisata.prod.repository.FavouriteRepository;
import com.wisata.prod.repository.UserRepository;
import com.wisata.prod.repository.DestinationRepository;
import com.wisata.prod.service.FavouriteService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final UserRepository userRepository;
    private final DestinationRepository destinationRepository;

    @Override
    public Favourites addFavourite(Long userId, Long destinationId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        Favourites favourite = new Favourites();
        favourite.setUser(user);
        favourite.setDestination(destination);

        return favouriteRepository.save(favourite);
    }

    @Override
    public List<Favourites> getFavouritesByUserId(Long userId) {
        return favouriteRepository.findByUserId(userId);
    }

    @Override
    public void removeFavourite(Long userId, Long destinationId) {
        FavouriteId favouriteId = new FavouriteId(userId, destinationId);
        favouriteRepository.deleteById(favouriteId);
    }
}