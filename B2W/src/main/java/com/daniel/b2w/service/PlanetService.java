package com.daniel.b2w.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.b2w.domain.Planet;
import com.daniel.b2w.dto.PlanetDTO;
import com.daniel.b2w.repository.PlanetRepository;
import com.daniel.b2w.service.exception.ObjectNotFoundException;

@Service
public class PlanetService {

	@Autowired
	private PlanetRepository planetRepository;

	public List<Planet> findAll() {
		return planetRepository.findAll();
	}

	public Planet findById(String id) {
		Optional<Planet> obj = planetRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public void delete(String id) {
		findById(id);
		planetRepository.deleteById(id);
	}

	public Planet insert(Planet obj) {
		return planetRepository.insert(obj);
	}

	public List<Planet> findByTitle(String text) {
		return planetRepository.searchTitle(text);
	}

	
	public Planet fromDTO(PlanetDTO objDto) {
		return new Planet(objDto.getId(), objDto.getName(), objDto.getClimate(), objDto.getTerrain());
	}

}
