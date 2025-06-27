package com.wisata.prod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wisata.prod.entity.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourites, com.wisata.prod.entity.FavouriteId> {
    List<Favourites> findByIdUser(Integer idUser);
    boolean existsByIdUserAndIdDestination(Integer idUser, Integer idDestination);
    @Transactional
    void deleteByIdUserAndIdDestination(Integer userId, Integer destinationId);
}