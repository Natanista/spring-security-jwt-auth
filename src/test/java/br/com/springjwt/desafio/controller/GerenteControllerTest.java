package br.com.springjwt.desafio.controller;

import br.com.springjwt.desafio.dto.GerenteLoginDTO;
import br.com.springjwt.desafio.model.Agendamento;
import br.com.springjwt.desafio.model.Cliente;
import br.com.springjwt.desafio.model.GerenteModel;
import br.com.springjwt.desafio.repository.AgendamentoRepository;
import br.com.springjwt.desafio.repository.ClienteRepository;
import br.com.springjwt.desafio.repository.GerenteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class GerenteControllerTest {

    @Autowired
    private GerenteController gerenteController;

    @MockBean
    ClienteRepository clienteRepository;

    @MockBean
    AgendamentoRepository agendamentoRepository;
    @MockBean
    private GerenteRepository gerenteRepository;

    private  LocalDate date = LocalDate.of(1990, 1, 8);


    @Test
    @DisplayName("signup deve retornar 200 caso cadastro seja sucesso")
    void signupDeveRetornar200() {
        //Given

        GerenteModel gerenteModel = new GerenteModel(
                null,
                "natan@outlook.com",
                "password",
                "password",
                "odontologia",
                "84153540021",
                date,
                "119283838484");
        //When
        ResponseEntity response = gerenteController.signup(gerenteModel);

        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    @DisplayName("signup deve retornar 409 caso email ja esteja cadastrado")
    void signupDeveRetornar409CasoEmailEstejaCadastrado() {
        //Given

        GerenteModel gerenteModel = new GerenteModel(
                null,
                "natan@natan.com",
                "password",
                "password",
                "odontologia",
                "98018527040",
                date,
                "119283838484");

        //When
        when(gerenteRepository.existsByEmail(gerenteModel.getEmail())).thenReturn(true);
        ResponseEntity response = gerenteController.signup(gerenteModel);

        //Then
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatusCodeValue());
    }

    @Test
    @DisplayName("signup deve retornar 409 caso cpf ja esteja cadastrado")
    void signupDeveRetornar409CasoCPFEstejaCadastrado() {
        //Given
        GerenteModel gerenteModel = new GerenteModel(
                null,
                "natan@natan.com",
                "password",
                "password",
                "odontologia",
                "98018527040",
                date,
                "119283838484");

        //When
        when(gerenteRepository.existsByCpf(gerenteModel.getCpf())).thenReturn(true);

        ResponseEntity response = gerenteController.signup(gerenteModel);

        //Then
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatusCodeValue());
    }

    @Test
    @DisplayName("signup deve retornar 401 caso confirmacao de senha esteja errada")
    void signupDeveRetornar401CasoSenhasEstejamErradas() {
        //Given
        GerenteModel gerenteModel = new GerenteModel(
                null,
                "natan@natan.com",
                "password",
                "password1",
                "odontologia",
                "98018527040",
                date,
                "119283838484");

        gerenteRepository.save(gerenteModel);
        //When
        ResponseEntity response = gerenteController.signup(gerenteModel);

        //Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
    }



    @Test
    @DisplayName("Login nao deve retornar status 401 caso credenciais nao conferem")
    void loginDeveRetornarStatus401CasoCredenciaisNaoConferem() {
        //GIVEN
        GerenteModel gerenteModel = new GerenteModel(
                null,
                "natan@natan.com",
                "password",
                "password",
                "odontologia",
                "98018527040",
                date,
                "119283838484");
        gerenteRepository.save(gerenteModel);
        GerenteLoginDTO gerenteLoginDTO = new GerenteLoginDTO(
                "natan@gmail.com", "password"
        );

        //WHEN
        ResponseEntity response = gerenteController.login(gerenteLoginDTO);

        //THEN
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Login deve retornar 401 caso gerente exista")
    void loginDeveRetornar401CasoGerenteNaoExista() {
        //GIVEN
        GerenteLoginDTO gerenteLoginDTO = new GerenteLoginDTO(
                "natan@gmail.com", "password"
        );

        //WHEN
        ResponseEntity response = gerenteController.login(gerenteLoginDTO);

        //THEN
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCodeValue());
    }

    @Test
    @DisplayName("attendance deve retornar 200 caso crie o agendamento")
    void attendace() {
        //given
        Agendamento agendamento = new Agendamento(
                null,
                LocalDateTime.of(
                        1999,
                        02,
                        2,
                        2,
                        2),
                new Cliente(
                        1L,
                        "Natan",
                        "98018527040",
                        new ArrayList<>()));
        //when
        ResponseEntity response = gerenteController.attendace(agendamento);

        //then
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }
}