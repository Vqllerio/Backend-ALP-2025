package com.wisata.prod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wisata.prod.entity.Favourite;
import com.wisata.prod.entity.FavouriteId;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
    List<Favourite> findByUserId(Long userId);
}