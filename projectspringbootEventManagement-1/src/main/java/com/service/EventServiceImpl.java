package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.CategoryRepository;
import com.dao.EventRepository;
import com.dao.VenueRepository;
import com.exceptiondemo.GlobalExceptionhandler;
import com.model.Category;
import com.model.Event;
import com.model.Venue;

@Service
public class EventServiceImpl  implements EventService{

	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private VenueRepository venueRepo;

	/*
	 @Override
	    public Page<Event> getEventsPaginated(Pageable pageable) {
	        return eventRepo.findAll(pageable);
	    }
	 
	 
	 @Override
	    public Page<Event> getEventsByCategory(Long categoryId, Pageable pageable) {
	        return eventRepo.findByCategoryId(categoryId, pageable);
	    }

	 
	 @Override
	    public Page<Event> searchEventsByTitle(String keyword, Pageable pageable) {
	        return eventRepo.findByTitleContainingIgnoreCase(keyword, pageable);
	    }
*/
	 
	@Override
	public List<Event> getAllEvents() {
	    return eventRepo.findAll();
	}

	@Override
	public Event getEventById(Long id) {
	    return eventRepo.findById(id).orElseThrow(() -> new RuntimeException("Event with ID " + id + " not found"));
	}

	@Override
	public Event createEvent(Event event) {
		 // Fetch the full Venue and Category from DB
		Venue venue = venueRepo.findById(event.getVenue().getId())
                .orElseThrow(() -> new RuntimeException("Venue not found"));
Category category = categoryRepo.findById(event.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
// Set the fetched Venue and Category to the Event
event.setVenue(venue);
event.setCategory(category);

// Save and return the event
return eventRepo.save(event);
	}

	@Override
	public Event updateEvent(Long id, Event eventDetails) {
		 Event existing = eventRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Event with ID " + id + " not found"));

	        // Update the fields
	        existing.setTitle(eventDetails.getTitle());
	       // existing.setDescription(eventDetails.getDescription());
	        existing.setDateTime(eventDetails.getDateTime());
	        existing.setVenue(eventDetails.getVenue());
	        existing.setCategory(eventDetails.getCategory());

	        return eventRepo.save(existing);
	}

	@Override
	public void deleteEvent(Long id) {
		 Event existing = eventRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Event with ID " + id + " not found"));
	        eventRepo.delete(existing);
	}

}
