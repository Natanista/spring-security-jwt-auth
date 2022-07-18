package br.com.springjwt.desafio.repository;

import br.com.springjwt.desafio.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
