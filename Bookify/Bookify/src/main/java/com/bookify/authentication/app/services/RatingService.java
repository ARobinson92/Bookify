package com.bookify.authentication.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookify.authentication.app.models.Rating;
import com.bookify.authentication.app.repositories.RatingRepository;

@Service
public class RatingService {
	@Autowired
	private RatingRepository ratingRepo;
	
	// get all
	public List<Rating> allRating(){
		return ratingRepo.findAll();
	}
	
	// find by id
	public Rating findById(Long id) {
		Optional<Rating> rating = ratingRepo.findById(id);
		
		if(rating.isPresent()) {
			return rating.get();
		} else {
			return null;
		}
	}
	
	// create and update
	public Rating save(Rating rating) {
		return ratingRepo.save(rating);
	}
	
	// delete
	public void delete(Long id) {
		ratingRepo.deleteById(id);
	}
}
