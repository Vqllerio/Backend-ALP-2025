package com.wisata.prod.service;

import com.wisata.prod.entity.Destination;
import com.wisata.prod.entity.dto.DestinationWithHistoryDTO;

import java.math.BigDecimal;
import java.util.List;

public interface DestinationService {
    Destination createDestination(Destination destination);

    Destination getDestinationById(Integer id);

    List<Destination> getAllDestinations();

    Destination updateDestination(Destination destination);

    void deleteDestination(Integer id);

    //methods to submit a rating for a destination
    Destination submitRating(Integer destinationId, BigDecimal rating);

    DestinationWithHistoryDTO getDestinationWithHistory(Integer id);

    DestinationWithHistoryDTO updateDestinationWithHistory(Integer id, DestinationWithHistoryDTO dto);

    DestinationWithHistoryDTO convertToDTO(Destination destination);
}
