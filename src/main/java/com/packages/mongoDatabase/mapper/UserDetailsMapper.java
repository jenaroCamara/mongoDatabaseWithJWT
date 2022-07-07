package com.packages.mongoDatabase.mapper;

import com.packages.mongoDatabase.role.domain.Role;
import com.packages.mongoDatabase.persona.domain.Persona;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsMapper {

    public static UserDetails build(Persona persona) {
        return new org.springframework.security.core.userdetails.User(persona.getName(), persona.getPassword(), getAuthorities(persona));
    }

    private static Set<? extends GrantedAuthority> getAuthorities(Persona retrievedUser) {
        Set<Role> roles = retrievedUser.getRoles();

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));

        return authorities;
    }
}
