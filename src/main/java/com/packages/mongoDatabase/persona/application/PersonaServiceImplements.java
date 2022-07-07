package com.packages.mongoDatabase.persona.application;

import com.packages.mongoDatabase.mapper.UserDetailsMapper;
import com.packages.mongoDatabase.persona.application.Port.PersonaServiceInterface;
import com.packages.mongoDatabase.persona.domain.Persona;
import com.packages.mongoDatabase.persona.infraestructure.dtopersona.PersonaDTOinput;
import com.packages.mongoDatabase.persona.infraestructure.dtopersona.PersonaDTOoutput;
import com.packages.mongoDatabase.persona.infraestructure.repository.PersonaRepository;
import com.packages.mongoDatabase.role.infraestructure.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class PersonaServiceImplements implements PersonaServiceInterface {
    @Autowired
    PersonaRepository personaRepository;
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PersonaDTOoutput create(PersonaDTOinput persona){
        //personaRepository.findById(persona.getId()).orElseThrow(()->new RuntimeException("Fallo al crear"));
        Persona personaEntity = modelMapper.map(persona,Persona.class);
        return modelMapper.map(personaRepository.save(personaEntity),PersonaDTOoutput.class);
    }

    /*@Override
    public List<PersonaDTOoutput> read(){
        List<Persona> lista = personaRepository.findAll();
        List<PersonaDTOoutput> lista2 = lista.stream()
                .map(persona -> modelMapper.map(persona,PersonaDTOoutput.class))
                .collect(Collectors.toList());
        return lista2;
    }*/

    @Override
    public Persona findByName(String username) throws Exception {
        List<Persona> listaPersona = personaRepository.findAll();//.orElseThrow(()-> new Exception(""));
        Persona persona = listaPersona.stream().filter(p -> username.equals(p.getId())).findFirst().orElseThrow(()-> new Exception("No encontrado"));
        return persona;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final Persona retrievedUser = personaRepository.findByName(userName);
        if (retrievedUser == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return UserDetailsMapper.build(retrievedUser);
    }

    @Override
    public Persona findById(int id) throws Exception {
        Persona persona = personaRepository.findById(id).orElseThrow(()-> new Exception(""));
        return persona;
    }

    /*@Override
    public PersonaDTOoutput update(PersonaDTOinput persona){
        //personaRepository.findById(persona.getId()).orElseThrow(()->new RuntimeException("Fallo al actualizar"));
        return modelMapper.map(personaRepository.save(modelMapper.map(persona,Persona.class)),PersonaDTOoutput.class);
    }

    @Override
    public Map<String, String> delete(int id){
        //Persona p = personaRepository.findById(id).orElseThrow(()->new RuntimeException("Fallo al crear"));

        // cuenta antes de borrar
        long beforeDelete = personaRepository.count();
        // borrado
        personaRepository.deleteById(id);
        // Cuenta despues de borrar
        long afterDelete = personaRepository.count();

        String messageValue = beforeDelete == afterDelete ? "Algo no ha salido bien!" : "Persona eliminada";

        Map<String, String> deleteMap = new HashMap<>();
        deleteMap.put("message", messageValue);

        return deleteMap;
    }*/

}
