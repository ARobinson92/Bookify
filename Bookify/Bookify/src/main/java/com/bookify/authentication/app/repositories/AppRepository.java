package com.bookify.authentication.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookify.authentication.app.models.Trip;

@Repository
public interface AppRepository extends CrudRepository<Trip, Long> {
	List<Trip> findAll();
}
