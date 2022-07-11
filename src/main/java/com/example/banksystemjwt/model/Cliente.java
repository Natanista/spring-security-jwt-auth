package com.example.banksystemjwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "cliente_id")
    private Long id;
    @NotEmpty(message = "Nome nao pode estar vazio e nem ser nulo")
    private String nome;
    @CPF
    @NotEmpty(message = "CPF nao pode estar vazio e nem ser nulo")
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Solicitacao> solicitacoes;

}
