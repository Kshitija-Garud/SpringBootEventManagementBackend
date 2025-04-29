package com.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.exceptiondemo.GlobalExceptionhandler;

import com.model.Attendee;
import com.model.AttendeeEventInfoDTO;

import com.service.AttendeeService;

@RestController
@RequestMapping("attendee")
@CrossOrigin(origins = "http://localhost:3000")
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @GetMapping("/getAll")

    public ResponseEntity<List<Attendee>> getAllAttendees() {
    	List<Attendee> AttendeeList=attendeeService.getAllAttendees();
		return ResponseEntity.ok(AttendeeList);
    		
    		}
    
    @GetMapping("/{id}")
    public ResponseEntity<Attendee> getAttendeeById(@PathVariable Long id) {
        try {
            Attendee attendee = attendeeService.getAttendeeById(id);
            return ResponseEntity.ok(attendee);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/add/{eventId}")
    public ResponseEntity<AttendeeEventInfoDTO> addAttendeeToEvent(@PathVariable Long eventId, @RequestBody Attendee attendee) {
        try {
            AttendeeEventInfoDTO dto = attendeeService.createAttendee(eventId, attendee);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .header("info", "Attendee added to event successfully")
                                 .body(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(null);
        }
    }
    
    

 //Update attendee details
    @PutMapping("/update/{id}")
    public ResponseEntity<Attendee> updateAttendee(
            @PathVariable Long id,
            @RequestBody Attendee updatedAttendee) {

        try {
            Attendee updated = attendeeService.updateAttendee(id, updatedAttendee);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    // Delete attendee by ID

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttendee(@PathVariable Long id) {
        try {
            attendeeService.deleteAttendee(id);
            return ResponseEntity.ok("Attendee deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendee not found.");
        }
    }

    /*

    // Search by name (paginated)
    @GetMapping("/search/name")
    public ResponseEntity<Page<Attendee>> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Attendee> result = attendeeService.searchByName(name, pageable);
        return ResponseEntity.ok(result);
    }

    //Search by email (paginated)
    @GetMapping("/search/email")
    public ResponseEntity<Page<Attendee>> searchByEmail(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Attendee> result = attendeeService.searchByEmail(email, pageable);
        return ResponseEntity.ok(result);
    }

    //Filter by eventId (paginated)
    @GetMapping("/filter")
    public ResponseEntity<Page<Attendee>> filterByEventId(
            @RequestParam Long eventId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Attendee> result = attendeeService.filterByEventId(eventId, pageable);
        return ResponseEntity.ok(result);
    }
    */
  

}





