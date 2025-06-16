package com.wisata.prod.service;

import com.wisata.prod.entity.Favourite;

import java.util.List;

public interface FavouriteService {
    Favourite addFavourite(Long userId, Long destinationId);
    List<Favourite> getFavouritesByUserId(Long userId);
    void removeFavourite(Long favouriteId);
}