package com.packages.mongoDatabase.persona.infraestructure.controller;

import com.packages.mongoDatabase.mapper.UserMapper;
import com.packages.mongoDatabase.persona.application.Port.PersonaServiceInterface;
import com.packages.mongoDatabase.persona.domain.Persona;
import com.packages.mongoDatabase.persona.infraestructure.dtopersona.PersonaDTOinput;
import com.packages.mongoDatabase.persona.infraestructure.dtopersona.PersonaDTOoutput;
import com.packages.mongoDatabase.webresponse.AuthorizationRequest;
import com.packages.mongoDatabase.webresponse.PersonaResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/persona")
@CrossOrigin
public class PersonaController {
    //@Autowired
    private PersonaServiceInterface personaService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public PersonaController(PersonaServiceInterface personaService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personaService = personaService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping
    public PersonaDTOoutput guardarPersona(@RequestBody PersonaDTOinput persona) {

        return personaService.create(persona);
    }

    /*@GetMapping
    public List<PersonaDTOoutput> getAllPeople() {
        return personaService.read();
    }

    @PutMapping
    public PersonaDTOoutput actualizaPersona(@RequestBody PersonaDTOinput persona) {

        return personaService.update(persona);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deletePersonaa(@PathVariable int id) {
        return personaService.delete(id);
    }*/


    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponse> getUser(@PathVariable int id) throws Exception {
        final Persona persona = personaService.findById(id);

        if (persona == null) {
            return ResponseEntity.notFound().build();
        }

        PersonaResponse userResponse = UserMapper.toResponse(persona);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Persona> saveUser(@RequestBody AuthorizationRequest userRequest) {
        userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        final PersonaDTOoutput userToSave = personaService.create(modelMapper.map(UserMapper.toDomain(userRequest),PersonaDTOinput.class));

        return new ResponseEntity<>(modelMapper.map(userToSave,Persona.class), HttpStatus.OK);
    }


}
