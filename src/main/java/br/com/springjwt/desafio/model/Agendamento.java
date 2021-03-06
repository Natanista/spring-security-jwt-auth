package br.com.springjwt.desafio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento implements Serializable  {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    @Future(message = "Os agendamentos deve ter data e hora futuros relativo a data e hroa atual")
    private LocalDateTime dataHora;

    @Valid
    @ManyToOne
    @JoinColumn(name="cliente_id", nullable=true)
    private Cliente cliente;
}
