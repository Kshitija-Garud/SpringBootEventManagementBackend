package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.model.Venue;
import com.service.VenueService;
import com.exceptiondemo.GlobalExceptionhandler;
@RestController
@RequestMapping("venues")
@CrossOrigin(origins = "http://localhost:3000") 
public class VenueController {

    @Autowired
    private VenueService venueService;

    
    @GetMapping("/getAll")
    public ResponseEntity<List<Venue>> getAllVenues() {
        List<Venue> venues = venueService.getAllVenues();
        return ResponseEntity.ok(venues);
    }
    /*
    // Get all venues with pagination
    @GetMapping("/getAll")
    public ResponseEntity<Page<Venue>> getAllVenues(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Venue> venuePage = venueService.getAllVenues(pageable);
        return ResponseEntity.ok(venuePage);
    }
*/
    // Get venue by ID
    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable Long id) {
        return venueService.getVenueById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /*
    // Search venues by name (case-insensitive)
    @GetMapping("/search/name")
    public ResponseEntity<List<Venue>> searchByName(@RequestParam String name) {
        List<Venue> venues = venueService.searchByName(name);
        return venues.isEmpty() 
            ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
            : ResponseEntity.ok(venues);
    }

    // Filter venues by location (case-insensitive)
    @GetMapping("/filter")
    public ResponseEntity<List<Venue>> filterByLocation(@RequestParam String location) {
        List<Venue> venues = venueService.filterByLocation(location);
        return venues.isEmpty() 
            ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
            : ResponseEntity.ok(venues);
    }
*/
    // Add a new venue
    @PostMapping("/add")
    public ResponseEntity<Venue> addVenue(@RequestBody Venue venue) {
        Venue createdVenue = venueService.createVenue(venue);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVenue);
    }

    // Update a venue by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Venue> updateVenue(@PathVariable Long id, @RequestBody Venue venueDetails) {
        try {
            Venue updatedVenue = venueService.updateVenue(id, venueDetails);
            return ResponseEntity.ok(updatedVenue);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete a venue by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVenue(@PathVariable Long id) {
        try {
            venueService.deleteVenue(id);
            return ResponseEntity.ok("Venue deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Venue not found.");
        }
    }
}
