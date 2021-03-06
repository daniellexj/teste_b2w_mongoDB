package com.daniel.b2w.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniel.b2w.domain.Planet;
import com.daniel.b2w.dto.PlanetDTO;
import com.daniel.b2w.resource.util.URL;
import com.daniel.b2w.service.PlanetService;

@RestController
@RequestMapping(value = "/planets")
public class PlanetResource {

	@Autowired
	private PlanetService planetService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PlanetDTO objDto) {
		Planet obj = planetService.fromDTO(objDto);
		obj = planetService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PlanetDTO>> findAll() {
		List<Planet> list = planetService.findAll();
		List<PlanetDTO> listDTO = list.stream().map(x -> new PlanetDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/findplanet", method=RequestMethod.GET)
 	public ResponseEntity<List<Planet>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Planet> list = planetService.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PlanetDTO> findById(@PathVariable String id) {
		Planet obj = planetService.findById(id);
		return ResponseEntity.ok().body(new PlanetDTO(obj));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		planetService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
