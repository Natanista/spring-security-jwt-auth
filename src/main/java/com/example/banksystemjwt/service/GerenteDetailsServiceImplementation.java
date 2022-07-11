package com.example.banksystemjwt.service;


import com.example.banksystemjwt.data.GerenteDetails;
import com.example.banksystemjwt.model.GerenteModel;
import com.example.banksystemjwt.repository.GerenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GerenteDetailsServiceImplementation implements UserDetailsService {

    private final GerenteRepository gerenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<GerenteModel> gerenteModelOptional =  gerenteRepository.findByEmail(email);
        if(gerenteModelOptional.isEmpty()){
            throw new UsernameNotFoundException("Email nao encontrado: " + email);
        }
        return new GerenteDetails(gerenteModelOptional);
    }


}
