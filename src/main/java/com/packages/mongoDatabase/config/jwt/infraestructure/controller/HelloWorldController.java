package com.packages.mongoDatabase.config.jwt.infraestructure.controller;

import com.packages.mongoDatabase.persona.infraestructure.controller.PersonaController;
import com.packages.mongoDatabase.persona.infraestructure.dtopersona.PersonaDTOinput;
import com.packages.mongoDatabase.persona.infraestructure.dtopersona.PersonaDTOoutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin()
public class HelloWorldController {

	@Autowired
	PersonaController personaController;

	@RequestMapping({ "/hello" })
	public String hello() {
		return "Hello World";
	}

	@PostMapping(name = "/persona",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonaDTOoutput guardarPersona(@RequestBody PersonaDTOinput persona) {

		return personaController.guardarPersona(persona);
	}

	@PutMapping(name = "/persona",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonaDTOoutput actualizaPersona(@RequestBody PersonaDTOinput persona) {

		return personaController.actualizaPersona(persona);
	}

	@DeleteMapping("/persona/{id}")
	public Map<String, String> deletePersona(@PathVariable String id) {
		return personaController.deletePersona(id);
	}
}
