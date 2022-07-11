package com.example.banksystemjwt.repository;

import com.example.banksystemjwt.model.GerenteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GerenteRepository extends JpaRepository<GerenteModel, Long> {
    Optional<GerenteModel> findByEmail(String email);

    Optional<GerenteModel> findGerenteByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
