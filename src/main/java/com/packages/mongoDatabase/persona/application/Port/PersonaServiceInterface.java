package com.packages.mongoDatabase.persona.application.Port;

import com.packages.mongoDatabase.persona.domain.Persona;
import com.packages.mongoDatabase.persona.infraestructure.dtopersona.PersonaDTOinput;
import com.packages.mongoDatabase.persona.infraestructure.dtopersona.PersonaDTOoutput;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;

public interface PersonaServiceInterface {
    PersonaDTOoutput create(PersonaDTOinput persona);
    /*List<PersonaDTOoutput> read();
    PersonaDTOoutput update(PersonaDTOinput persona);
    Map<String, String> delete(int id);*/
    Persona findByName(String username) throws Exception;
    Persona findById(int id) throws Exception;
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
}
