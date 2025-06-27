package com.wisata.prod.service;

import com.wisata.prod.entity.Favourites;

import java.util.List;

public interface FavouriteService {
    Favourites addFavourite(Integer userId, Integer destinationId);

    List<Favourites> getFavouritesByUserId(Long userId);

    void removeFavourite(Long userId, Long destinationId);
}