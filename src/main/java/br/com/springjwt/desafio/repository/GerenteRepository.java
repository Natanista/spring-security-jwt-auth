package br.com.springjwt.desafio.repository;

import br.com.springjwt.desafio.model.GerenteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GerenteRepository extends JpaRepository<GerenteModel, Long> {
    Optional<GerenteModel> findByEmail(String email);

    Optional<GerenteModel> findGerenteModelByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
