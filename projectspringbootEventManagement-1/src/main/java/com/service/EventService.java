package com.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.model.Event;

public interface EventService {
	 // Fetch all events without pagination
	List<Event> getAllEvents(); 
	
	 
	
    // Get a single event by its ID
	Event getEventById(Long id);
	
	 // Create and save a new event
	Event createEvent(Event event);
	
	
	  // Update an existing event using ID
	Event updateEvent(Long id, Event eventDetails);
	
	 // Delete an event by ID
	void deleteEvent(Long id); 
	
	 // Search events by title with pagination
    Page<Event> searchEventsByTitle(String keyword, Pageable pageable);

    // Filter events by category name with pagination
    Page<Event> filterByCategoryName(String categoryName, Pageable pageable);

    // Filter events by venue name with pagination
    Page<Event> filterByVenueName(String venueName, Pageable pageable);
    
    
 // Find events that occur on a specific date (ignoring time)
    Page<Event> findByDateTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay, Pageable pageable);


}
