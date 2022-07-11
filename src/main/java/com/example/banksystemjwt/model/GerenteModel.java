package com.example.banksystemjwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GerenteModel {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @NotEmpty(message = "O campo email deve ser preenchido e nao pode ser null")
    @Email(message = "Email invalido!")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "O campo de senha deve ser preenchido e nao pode ser null")

    @Size(max=100,min=8,message="A senha deve conter no minimo 8 caracteres")
    private String senha;

    @NotEmpty(message = "O campo confirmacao de senha deve ser preenchido e nao pode ser null")
    private String confirmacaoSenha;

    @NotEmpty(message = "O campo setor deve ser preenchido e nao pode ser null")
    private String setor;

    @CPF(message = "CPF Invalido!")
    @NotEmpty(message = "O campo CPF deve ser preenchido e nao pode ser null")
    @Column(unique = true)
    private String cpf;

    @Past(message = "Nao e possivel cadastrar datas de nascimento acima da data atual")
    private LocalDate dataNascimento;

    @NotEmpty(message = "O campo telefone de nascimento deve ser preenchido e nao pode ser null")
    private String telefone;

}
