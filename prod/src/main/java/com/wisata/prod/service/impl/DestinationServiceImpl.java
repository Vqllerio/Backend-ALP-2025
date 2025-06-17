package com.wisata.prod.service.impl;

import lombok.AllArgsConstructor;
import com.wisata.prod.entity.Destination;
import com.wisata.prod.repository.DestinationRepository;
import com.wisata.prod.service.DestinationService;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public Destination getDestinationById(Long destinationId) {
        return destinationRepository.findById(destinationId)
            .orElseThrow(() -> new RuntimeException("Destination not found with id: " + destinationId));
    }

    @Override
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination updateDestination(Destination destination) {
        Destination existingDestination = destinationRepository.findById(destination.getId())
            .orElseThrow(() -> new RuntimeException("Destination not found with id: " + destination.getId()));
        existingDestination.setTitle(destination.getTitle());
        existingDestination.setLocation(destination.getLocation());
        existingDestination.setDescription(destination.getDescription());
        existingDestination.setImage(destination.getImage());
        existingDestination.setCategory(destination.getCategory());
        Destination updatedDestination = destinationRepository.save(existingDestination);
        return updatedDestination;
    }

    @Override
    public void deleteDestination(Long destinationId) {
        destinationRepository.deleteById(destinationId);
    }

    //Rating submission logic
    @Override
    // Takes destinationId and rating as parameters
    public Destination submitRating(Long destinationId, int rating) {
        Destination destination = getDestinationById(destinationId);
        float currentRating = destination.getRating();
        int currentReviews = destination.getReviews();
        int currentUserRating = destination.getUserRating();

        // Update the destination's rating
        float newRating = ((currentRating * currentReviews) + rating) / (currentReviews + 1);
        // Round to one decimal place
        newRating = Math.round(newRating * 10) / 10.0f;
        // Update the destination's reviews and userRating
        destination.setRating(newRating);
        destination.setReviews(currentReviews + 1);
        destination.setUserRating(currentUserRating + 1);
        // Save the updated destination
        return destinationRepository.save(destination);
    }
}