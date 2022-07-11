package com.example.banksystemjwt.data;

import com.example.banksystemjwt.model.GerenteModel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class GerenteDetails implements UserDetails {

    private final Optional<GerenteModel> gerenteModelOptional;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return gerenteModelOptional.orElse(new GerenteModel()).getSenha();
    }

    @Override
    public String getUsername() {
        return  gerenteModelOptional.orElse(new GerenteModel()).getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
