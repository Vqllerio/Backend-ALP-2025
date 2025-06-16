package com.wisata.prod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wisata.prod.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

}