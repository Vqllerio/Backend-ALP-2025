package com.wisata.prod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wisata.prod.entity.Favourites;
import com.wisata.prod.entity.FavouriteId;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourites, FavouriteId> {
    List<Favourites> findByUserId(Integer userId);

    // Check if a favorite exists
    boolean existsByIdUserAndIdDestination(Integer userId, Integer destinationId);

    // Delete a favorite
    @Modifying
    @Query("DELETE FROM Favourites f WHERE f.idUser = :userId AND f.idDestination = :destinationId")
    void deleteByIdUserAndIdDestination(@Param("userId") Integer userId,
            @Param("destinationId") Integer destinationId);

    List<Favourites> findByIdUser(Integer idUser);
}