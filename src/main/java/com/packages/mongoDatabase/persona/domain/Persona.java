package com.packages.mongoDatabase.persona.domain;

import com.packages.mongoDatabase.role.domain.Role;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Getter
@Entity
@Builder
public class Persona {
    @Id //usar generador de codigo automatico
    @GeneratedValue
    int id;
    @Column
    String usuario;
    @Column
    String name;
    @Column
    String password;
    @Column
    String surname;
    @Column
    String company_email;
    @Column
    String personal_email;
    @Column
    String city;
    @Column
    boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;
    boolean admin;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles;
}
