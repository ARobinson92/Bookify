package com.bookify.authentication.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookify.authentication.app.models.Trip;
import com.bookify.authentication.app.repositories.AppRepository;

@Service
public class AppService {

	private final AppRepository appRepository;

	public AppService(AppRepository appRepository) {
		this.appRepository = appRepository;
	}

	public List<Trip> getAll() {
		return appRepository.findAll();
	}

	public Trip getOne(Long id) {
		Optional<Trip> trip = appRepository.findById(id);
		
		if(trip.isPresent()) {
			return trip.get();
		} else {
			return null;
		}
	}

	public Trip save(Trip trip) {
		return appRepository.save(trip);
	}

	public void deleteTrip(Long id) {
		appRepository.deleteById(id);
	}
}