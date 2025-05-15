package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	 // Search by event title (case-insensitive)
    Page<Event> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
 // Filter by category name (case-insensitive)
    Page<Event> findByCategory_NameContainingIgnoreCase(String categoryName, Pageable pageable);

    // Filter by venue name (case-insensitive)
    Page<Event> findByVenue_NameContainingIgnoreCase(String venueName, Pageable pageable);
    
 // Find events that occur on a specific date (ignoring time)
    Page<Event> findByDateTimeBetween(LocalDateTime startOfDay, LocalDateTime endOfDay, Pageable pageable);

}
