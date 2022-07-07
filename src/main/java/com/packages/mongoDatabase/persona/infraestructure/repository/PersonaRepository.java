package com.packages.mongoDatabase.persona.infraestructure.repository;

import com.packages.mongoDatabase.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Persona findByName(String name);
}
