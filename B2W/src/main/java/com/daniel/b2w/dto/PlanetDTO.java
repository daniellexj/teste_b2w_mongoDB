package com.daniel.b2w.dto;

import java.io.Serializable;

import com.daniel.b2w.domain.Planet;

public class PlanetDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String climate;
	private String terrain;

	public PlanetDTO() {

	}

	public PlanetDTO(Planet obj) {
		id = obj.getId();
		name = obj.getName();
		climate = obj.getClimate();
		terrain = obj.getTerrain();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

}
