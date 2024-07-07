package com.gvgroup.ordermanagement.security.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.gvgroup.ordermanagement.security.config.SecurityConstants.AUTHORITIES_CLAIM_NAME;

public class JwtRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<String> authorities = (Collection<String>) jwt.getClaims().get(AUTHORITIES_CLAIM_NAME);
        if (authorities == null || authorities.isEmpty()) {
            return Collections.emptyList();
        }
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
    }


}