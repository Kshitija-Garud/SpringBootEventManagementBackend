package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.exceptiondemo.GlobalExceptionhandler;
import com.model.Event;
import com.service.EventService;

@RestController
@RequestMapping("events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

	
	
	 @Autowired
	    private EventService eventService;

	    //Get all events
	 @GetMapping("/getAll")
	    public ResponseEntity<List<Event>> getAllEvents() {
	        try {
	            List<Event> events = eventService.getAllEvents();
	            return ResponseEntity.ok(events);
	        } catch (Exception e) {
	            throw new RuntimeException("Internal Server Error"); // Simulate 500
	        }
	    }
/*
	    //  Get paginated list of events
	    @GetMapping("/paginated")
	    public ResponseEntity<Page<Event>> getPaginatedEvents(
	            @RequestParam(defaultValue = "0") int page,           // page number
	            @RequestParam(defaultValue = "5") int size,           // events per page
	            @RequestParam(defaultValue = "id") String sortBy      // sorting field
	    ) {
	    	// Creating a Pageable object with page number, size, and sorting field
	        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        
	        // Fetching the events with pagination
	        Page<Event> eventPage = eventService.getEventsPaginated(pageable);
	        
	     // Returning the paginated events as a response
	        return ResponseEntity.ok(eventPage);
	    }

	    // Search events by title (case-insensitive)
	    @GetMapping("/search")
	    public ResponseEntity<Page<Event>> searchEvents(
	            @RequestParam String keyword,					// Keyword to search in event title
	            @RequestParam(defaultValue = "0") int page,		// Page number
	            @RequestParam(defaultValue = "5") int size		// Number of records per page
	    ) {
	    	
	    	 // Creating a Pageable object with page number and size
	        Pageable pageable = PageRequest.of(page, size);
	        
	        // Searching events with a case-insensitive title match
	        Page<Event> results = eventService.searchEventsByTitle(keyword, pageable);
	        
	     // Returning the search results
	        return ResponseEntity.ok(results);
	    }

	    // Filter events by category ID
	    @GetMapping("/filter")
	    public ResponseEntity<Page<Event>> filterByCategory(
	            @RequestParam Long categoryId,					// Category ID to filter events by
	            @RequestParam(defaultValue = "0") int page,		// Page number
	            @RequestParam(defaultValue = "5") int size		// Number of records per page

	    ) {
	    	
	        // Creating a Pageable object for pagination
	        Pageable pageable = PageRequest.of(page, size);
	        
	        // Filtering events by category ID
	        Page<Event> filteredEvents = eventService.getEventsByCategory(categoryId, pageable);
	        
	     // Returning the filtered events
	        return ResponseEntity.ok(filteredEvents);
	    }
*/
	    // Get event by ID
	 @GetMapping("/{id}")
	    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
	        try {
	            Event event = eventService.getEventById(id);
	            return ResponseEntity.ok(event);
	        } catch (Exception e) {
	            throw new RuntimeException("Internal Server Error"); // Simulate 500
	        }
	    }

	    //Create a new event
	    @PostMapping("/create")
	    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
	        Event created = eventService.createEvent(event); // Create event
	        return ResponseEntity.status(HttpStatus.CREATED) // Send HTTP 201 status
	                             .header("info", "Event created successfully")
	                             .body(created); // Return the created event as the response
	    }

	    // Update existing event by ID
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
	        try {
	            Event updated = eventService.updateEvent(id, eventDetails);
	            return ResponseEntity.ok(updated);
	        } catch (Exception e) {
	            throw new RuntimeException("Internal Server Error"); // Simulate 500
	        }
	    }

	    // Delete event by ID
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
	        try {
	            eventService.deleteEvent(id);
	            return ResponseEntity.ok("Event deleted successfully.");
	        } catch (Exception e) {
	            throw new RuntimeException("Internal Server Error"); // Simulate 500
	        }
	    } 
}
