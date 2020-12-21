package com.bookify.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookify.app.models.Trip;

@Repository
public interface AppRepository extends CrudRepository<Trip, Long> {
	List<Trip> findAll();
}
