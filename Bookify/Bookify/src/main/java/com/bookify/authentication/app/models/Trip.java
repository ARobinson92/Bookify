package com.bookify.authentication.app.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.bookify.authentication.models.User;

@Entity
@Table(name="trips")
public class Trip {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	@Size(min = 2, message = "Destination should be at least 2 characters.")
	private String destination;
	@Column
	private	Long creator_id;
	@Column
	@DateTimeFormat(pattern  = "MM/dd/yyyy")
	private Date departure;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
				name = "users_trips", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "trip_id")
				)
	private List<User> users;
	@OneToMany(mappedBy="trip", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;
	@Column(updatable = false)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date created_at;
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date updated_at;
	
	
	public Trip(String destination, Date departure, Long creator) {
		this.destination = destination;
		this.departure = departure;
		this.creator_id = creator;
	}
	
	public Trip(String destination, Date departure) {
		this.destination = destination;
		this.departure = departure;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
  
	public Long getCreator_id() {
		return creator_id;
	}

	public void setCreator(Long creator) {
		this.creator_id = creator;
	}

	@PrePersist
	protected void onCreate() {
		this.created_at = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated_at = new Date();
	}
}