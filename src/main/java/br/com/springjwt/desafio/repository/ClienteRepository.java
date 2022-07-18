package br.com.springjwt.desafio.repository;

import br.com.springjwt.desafio.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    boolean existsClienteByCpf(String cpf);

    Cliente getClienteByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
