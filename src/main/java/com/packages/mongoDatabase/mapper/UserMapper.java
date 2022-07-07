package com.packages.mongoDatabase.mapper;

import com.packages.mongoDatabase.persona.domain.Persona;
import com.packages.mongoDatabase.webresponse.AuthorizationRequest;
import com.packages.mongoDatabase.webresponse.PersonaResponse;

public class UserMapper {

    private UserMapper() {
    }

    public static PersonaResponse toResponse(Persona persona) {
        return PersonaResponse.builder().name(persona.getName()).id(persona.getId()).build();
    }

    public static Persona toDomain(AuthorizationRequest authorizationRequest) {
        return Persona.builder().name(authorizationRequest.getUserName()).password(authorizationRequest.getPassword())
                .build();
    }
}