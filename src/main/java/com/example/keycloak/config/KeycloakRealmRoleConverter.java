package com.example.keycloak.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final String clientId;

    public KeycloakRealmRoleConverter(
            @Value("${app.keycloak.client-id}") String clientId) {
        this.clientId = clientId;
    }

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<String> roles = new ArrayList<>();

        // realm roles
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.get("roles") instanceof Collection<?> collection) {
            collection.forEach(r -> roles.add(String.valueOf(r)));
        }

        // 2) Client roles
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null && resourceAccess.get(clientId) instanceof Map<?,?> client) {
            Object clientRoles = ((Map<?,?>) client).get("roles");
            if (clientRoles instanceof Collection<?> c) {
                c.forEach(r -> roles.add(String.valueOf(r)));
            }
        }

        // Prefix ROLE_
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.toUpperCase()))
                .collect(Collectors.toSet());
    }
}
