package com.packages.mongoDatabase.webresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class PersonaResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

}