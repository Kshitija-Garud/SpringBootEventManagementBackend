package com.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private LocalDateTime dateTime;

	@Lob
	@Column(length=200000)
	private byte[] imageData;
	
    private LocalDateTime lastRegistrationDate;

    
	@OneToMany(mappedBy = "event")
	@JsonIgnore
	private List<Attendee> attendees;
	
	@ManyToOne()
	@JoinColumn(name = "venue_id")
	private Venue venue;
	
	@ManyToOne()
	@JoinColumn(name = "category_id")
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	  public byte[] getImageData() {
	        return imageData;
	    }

	    public void setImageData(byte[] imageData) {
	        this.imageData = imageData;
	    }

	    public LocalDateTime getLastRegistrationDate() {
	        return lastRegistrationDate;
	    }

	    public void setLastRegistrationDate(LocalDateTime localDateTime) {
	        this.lastRegistrationDate = localDateTime;
	    }
	public List<Attendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Attendee> attendees) {
		this.attendees = attendees;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}