package com.wisata.prod.service;

import com.wisata.prod.entity.Destination;

import java.util.List;

public interface DestinationService {
    Destination createDestination(Destination destination);

    Destination getDestinationById(Long id);

    List<Destination> getAllDestinations();

    Destination updateDestination(Destination destination);

    void deleteDestination(Long id);
}
