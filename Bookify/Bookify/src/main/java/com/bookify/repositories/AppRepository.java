package com.bookify.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookify.models.Trip;

@Repository
public interface AppRepository extends CrudRepository<Trip, Long> {
	List<Trip> findAll();
}
