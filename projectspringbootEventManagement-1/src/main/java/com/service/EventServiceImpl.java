package com.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dao.CategoryRepository;
import com.dao.EventRepository;
import com.dao.VenueRepository;
import com.exceptiondemo.CategoryNotFoundException;
import com.exceptiondemo.EventNotFoundException;
import com.exceptiondemo.VenueNotFoundException;
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

	
	 
	@Override
	public List<Event> getAllEvents() {
	    return eventRepo.findAll();
	}

	@Override
	public Event getEventById(Long id) {
	    return eventRepo.findById(id).orElseThrow(() -> new EventNotFoundException("Event with ID " + id + " not found"));
	}

	@Override
	public Event createEvent(Event event) {
		 // Fetch the full Venue and Category from DB
		Venue venue = venueRepo.findById(event.getVenue().getId())
		                .orElseThrow(() -> new VenueNotFoundException("Venue not found"));
		
Category category = categoryRepo.findById(event.getCategory().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

// Set the fetched Venue and Category to the Event
event.setVenue(venue);
event.setCategory(category);

// Save and return the event
return eventRepo.save(event);
	}

	@Override
	public Event updateEvent(Long id, Event eventDetails) {
		 Event existing = eventRepo.findById(id)
	                .orElseThrow(() -> new EventNotFoundException("Event with ID " + id + " not found"));

	        // Update the fields
	        existing.setTitle(eventDetails.getTitle());
	       // existing.setDescription(eventDetails.getDescription());
	        existing.setDateTime(eventDetails.getDateTime());
	        existing.setVenue(eventDetails.getVenue());
	        existing.setCategory(eventDetails.getCategory());
	        existing.setImageData(eventDetails.getImageData()); 
	        existing.setLastRegistrationDate(eventDetails.getLastRegistrationDate()); 

	        return eventRepo.save(existing);
	}

	@Override
	public void deleteEvent(Long id) {
		 Event existing = eventRepo.findById(id)
	                .orElseThrow(() -> new EventNotFoundException("Event with ID " + id + " not found"));
	        eventRepo.delete(existing);
	}
	
	 // Search events by title with pagination
    @Override
    public Page<Event> searchEventsByTitle(String keyword, Pageable pageable) {
        return eventRepo.findByTitleContainingIgnoreCase(keyword, pageable);
    }

    @Override
    public Page<Event> filterByCategoryName(String categoryName, Pageable pageable) {
        return eventRepo.findByCategory_NameContainingIgnoreCase(categoryName, pageable);
    }

    @Override
    public Page<Event> filterByVenueName(String venueName, Pageable pageable) {
        return eventRepo.findByVenue_NameContainingIgnoreCase(venueName, pageable);
    }
   
    @Override
    public Page<Event> findByDateTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay, Pageable pageable) {
        return eventRepo.findByDateTimeBetween(startOfDay, endOfDay, pageable);
    }


}
