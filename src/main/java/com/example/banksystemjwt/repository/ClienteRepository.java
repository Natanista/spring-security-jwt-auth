package com.example.banksystemjwt.repository;


import com.example.banksystemjwt.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    

    Cliente getClienteByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
