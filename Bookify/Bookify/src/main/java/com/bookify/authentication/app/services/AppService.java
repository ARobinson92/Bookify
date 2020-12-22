package com.bookify.authentication.app.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookify.authentication.app.models.Trip;
import com.bookify.authentication.app.repositories.AppRepository;

@Service
public class AppService {

	private AppRepository appRepository;

	public AppService(AppRepository appRepository) {
		this.appRepository = appRepository;
	}

	public List<Trip> getAll() {
		return (List<Trip>) appRepository.findAll();
	}

	public Optional<Trip> getOne(Long id) {
		return appRepository.findById(id);
	}

	public void addTrip(Trip trip) {
		appRepository.save(trip);
	}

	public void updateTrip(Trip trip) {
		appRepository.save(trip);
	}

	public void deleteTrip(Long id) {
		appRepository.deleteById(id);
	}

	public List<Trip> topTen() {
		ArrayList<Trip> trips = (ArrayList<Trip>) appRepository.findAll();
		Collections.sort(trips, Comparator.comparingInt(Trip::getRating).reversed());
		for (int i = 0; i < trips.size(); i++) {
			if (i > 9) {
				trips.remove(i);
			}
		}
		return trips;
	}
}
