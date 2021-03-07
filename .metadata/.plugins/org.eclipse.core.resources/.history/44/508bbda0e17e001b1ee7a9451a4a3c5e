package com.daniel.b2w.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.daniel.b2w.domain.Planet;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

	@Query("{ 'name': { $regex: ?0, $options: 'i' } }")
	List<Planet> searchTitle(String text);
}
