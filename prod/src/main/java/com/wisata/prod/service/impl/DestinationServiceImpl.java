package com.wisata.prod.service.impl;

import lombok.AllArgsConstructor;
import com.wisata.prod.entity.Destination;
import com.wisata.prod.entity.dto.DestinationWithHistoryDTO;
import com.wisata.prod.repository.DestinationRepository;
import com.wisata.prod.service.DestinationService;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DestinationServiceImpl implements DestinationService {

    private DestinationRepository destinationRepository;

    @Override
    public Destination createDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    @Override
    public Destination getDestinationById(Integer destinationId) {
        return destinationRepository.findById(destinationId)
            .orElseThrow(() -> new RuntimeException("Destination not found with id: " + destinationId));
    }

    @Override
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination updateDestination(Destination destination) {
        Destination existingDestination = destinationRepository.findById(destination.getIdDestination())
            .orElseThrow(() -> new RuntimeException("Destination not found with id: " + destination.getIdDestination()));
        existingDestination.setTitle(destination.getTitle());
        existingDestination.setLocation(destination.getLocation());
        existingDestination.setDescription(destination.getDescription());
        existingDestination.setImage(destination.getImage());
        existingDestination.setCategory(destination.getCategory());
        Destination updatedDestination = destinationRepository.save(existingDestination);
        return updatedDestination;
    }

    @Override
    public void deleteDestination(Integer destinationId) {
        destinationRepository.deleteById(destinationId);
    }

    //Rating submission logic
    // Takes destinationId and rating as parameters
    public Destination submitRating(Integer destinationId, BigDecimal rating) {
        Destination destination = getDestinationById(destinationId);
        BigDecimal currentRating = destination.getRating();
        Integer currentReviews = destination.getReviews();

        // Calculate new rating: ((currentRating * currentReviews) + rating) / (currentReviews + 1)
        BigDecimal totalRating = currentRating.multiply(BigDecimal.valueOf(currentReviews)).add(rating);
        Integer newReviews = currentReviews + 1;
        BigDecimal newRating = totalRating.divide(BigDecimal.valueOf(newReviews), 4, BigDecimal.ROUND_HALF_UP);

        // Update the destination's rating and reviews
        destination.setRating(newRating);
        destination.setReviews(newReviews);

        // Save the updated destination
        return destinationRepository.save(destination);
    }

    // Update comprehensive data
    public DestinationWithHistoryDTO updateDestinationWithHistory(
            Integer id,
            DestinationWithHistoryDTO dto) {

        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found"));

        // Update main fields
        destination.setTitle(dto.getTitle());
        destination.setLocation(dto.getLocation());
        destination.setDescription(dto.getDescription());
        destination.setImage(dto.getImage());
        destination.setRating(dto.getRating());
        destination.setReviews(dto.getReviews());
        destination.setCategory(dto.getCategory());

        // Update history fields
        destination.setHistoryTitle(dto.getHistoryTitle());
        destination.setHistoryContent(dto.getHistoryContent());
        destination.setHistoryImage(dto.getHistoryImage());

        Destination updated = destinationRepository.save(destination);
        return convertToDTO(updated);
    }

    public DestinationWithHistoryDTO convertToDTO(Destination destination) {
        DestinationWithHistoryDTO dto = new DestinationWithHistoryDTO();
        dto.setIdDestination(destination.getIdDestination());
        dto.setTitle(destination.getTitle());
        dto.setLocation(destination.getLocation());
        dto.setDescription(destination.getDescription());
        dto.setImage(destination.getImage());
        dto.setRating(destination.getRating());
        dto.setReviews(destination.getReviews());
        dto.setCategory(destination.getCategory());
        dto.setHistoryTitle(destination.getHistoryTitle());
        dto.setHistoryContent(destination.getHistoryContent());
        dto.setHistoryImage(destination.getHistoryImage());

        return dto;
    }

    @Override
    public DestinationWithHistoryDTO getDestinationWithHistory(Integer id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found"));

        return convertToDTO(destination);
    }
}

class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
