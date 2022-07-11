package com.example.banksystemjwt.controller;


import com.example.banksystemjwt.dto.GerenteLoginDTO;
import com.example.banksystemjwt.model.Cliente;
import com.example.banksystemjwt.model.GerenteModel;
import com.example.banksystemjwt.model.Solicitacao;
import com.example.banksystemjwt.repository.ClienteRepository;
import com.example.banksystemjwt.repository.GerenteRepository;
import com.example.banksystemjwt.repository.SolicitacaoRepository;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertNull;

@SpringBootTest
@AutoConfigureMockMvc
class GerenteControllerTest {

    @Autowired
    private GerenteController gerenteController;



    @MockBean
    ClienteRepository clienteRepository;

    @MockBean
    SolicitacaoRepository solicitacaoRepository;
    @MockBean
    private GerenteRepository gerenteRepository;

    private final LocalDate date = LocalDate.of(1990, 1, 8);


    @Test
    @DisplayName("signup deve retornar 200 caso cadastro seja sucesso")
    void signupDeveRetornar200() {
        //Given

        GerenteModel gerenteModel = new GerenteModel(
                null,
                "natan@outlook.com",
                "password",
                "password",
                "TI",
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
                "Plataforma",
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
                "Pagamentos",
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
                "TI",
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
                "TI",
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
    @DisplayName("attendance deve retornar 200 caso crie a solicitacao")
    void attendace() {
        //given
        Solicitacao solicitacao = new Solicitacao(
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
        ResponseEntity response = gerenteController.criarSolicitacao(solicitacao);

        //then
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    @DisplayName("getSolicitacoes deve retornar 204 caso nao tenha solicitacoes cadastradas")
    void getSolicitacoesDeveRetornar204CasoNaoTenhaSolicitacoesCadastradas(){
        List<Solicitacao> solicitacaoList = new ArrayList<>();
        //when
        when(solicitacaoRepository.findAll()).thenReturn(solicitacaoList);
        ResponseEntity response = gerenteController.getSolicitacoes();

        //then
        Assertions.assertNull(response.getBody());
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
    }

    @Test
    @DisplayName("getSolicitacoes deve retornar 200 e corpo caso tenha solicitacoes cadastradas")
    void getSolicitacoesDeveRetornar200ECorpoCasoTenhaSolicitacoesCadastradas(){
        List<Solicitacao> solicitacaoList = new ArrayList<>();
        solicitacaoList.add(mock(Solicitacao.class));
        //when
        when(solicitacaoRepository.findAll()).thenReturn(solicitacaoList);
        ResponseEntity response = gerenteController.getSolicitacoes();

        //then
        assertEquals(solicitacaoList, response.getBody());
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }
}