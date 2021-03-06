package com.daniel.b2w.resouce.test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import com.daniel.b2w.domain.Planet;
import com.daniel.b2w.resource.PlanetResource;
import com.daniel.b2w.service.PlanetService;

import io.restassured.http.ContentType;

@WebMvcTest
public class PlanetResourceTest {

	@Autowired
	private PlanetResource planetResource;

	@MockBean
	private PlanetService planetService;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.planetResource);
	}

	@Test
	public void retornaSucesso_BuscaPorID() {

		when(this.planetService.findById("6043f0ab736fe9619440cda5"))
				.thenReturn(new Planet("6043f0ab736fe9619440cda5", "Alderaan", "temperate", "grasslands, mountains"));

		given().accept(ContentType.JSON).when().get("/planets/").then().statusCode(HttpStatus.OK.value());

	}

	@Test
	public void retornaSucesso_BuscaPorNome() {

		String text = "Yavin%20IV";
		when(this.planetService.findByTitle(text)).thenReturn(
				findListPlanet("6043f0ab736fe9619440cda5", "Alderaan", "temperate", "grasslands, mountains"));
		given().accept(ContentType.JSON).when().get("/planets/").then().statusCode(HttpStatus.OK.value());

	}

	@Test
	public void retornaSucesso_BuscaPorTotal() {

		when(this.planetService.findAll()).thenReturn(
				findListPlanet("6043f0ab736fe9619440cda5", "Alderaan", "temperate", "grasslands, mountains"));
		given().accept(ContentType.JSON).when().get("/planets/").then().statusCode(HttpStatus.OK.value());

	}

	@Test
	public void retornaSucesso_DeletePorID() {

		Planet planet = new Planet();
		planet.setId("60440c75b6c5c91c99bff39b");
		when(this.planetService.findById(planet.getId())).thenReturn(null);
		this.planetService.delete(planet.getId());
		verify(this.planetService).delete(planet.getId());
	}

	@Test
	public void retornaSucesso_InseriPlaneta() {

		Planet planet = new Planet();
		planet.setName("Yavin IV");
		when(this.planetService.insert(ArgumentMatchers.any(Planet.class))).thenReturn(planet);
		Planet created = planetService.insert(planet);
		assertThat(created.getName()).isSameAs(planet.getName());
		verify(planetService).insert(planet);

	}

	private List<Planet> findListPlanet(String id, String name, String climate, String terrain) {
		List<Planet> list = new ArrayList<>();
		Planet planet = new Planet();
		planet.setId(id);
		planet.setName(name);
		planet.setClimate(climate);
		planet.setTerrain(terrain);
		list.add(planet);
		return list;
	}

}
