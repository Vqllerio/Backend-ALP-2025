package com.wisata.prod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wisata.prod.entity.Favourite;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    List<Favourite> findByUserId(Long userId);
}