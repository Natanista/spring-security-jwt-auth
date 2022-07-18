package br.com.springjwt.desafio.service;

import br.com.springjwt.desafio.data.GerenteDetails;
import br.com.springjwt.desafio.model.GerenteModel;
import br.com.springjwt.desafio.repository.GerenteRepository;
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
        Optional<GerenteModel> gerenteModelOptinal =  gerenteRepository.findByEmail(email);
        if(gerenteModelOptinal.isEmpty()){
            throw new UsernameNotFoundException("Email nao encontrado: " + email);
        }
        return new GerenteDetails(gerenteModelOptinal);
    }


}
