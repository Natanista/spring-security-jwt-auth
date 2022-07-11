package com.example.banksystemjwt.controller;


import com.example.banksystemjwt.dto.GerenteLoginDTO;
import com.example.banksystemjwt.model.Solicitacao;
import com.example.banksystemjwt.model.GerenteModel;
import com.example.banksystemjwt.utils.Routes;
import com.example.banksystemjwt.service.GerenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Routes.PATH_V1)
@AllArgsConstructor
public class GerenteController {

    private GerenteService gerenteService;

    @PostMapping(Routes.PAHT_SIGNUP)
    public ResponseEntity signup(
            @Valid @RequestBody GerenteModel gerenteModel){
        return gerenteService.signup(gerenteModel);
    }

    @PostMapping(Routes.PATH_LOGIN)
    public ResponseEntity login(
            @Valid @RequestBody GerenteLoginDTO gerenteLoginDTO
    ){
        return gerenteService.login(gerenteLoginDTO);
    }

    @PostMapping(Routes.PATH_SOLICITACOES)
    public ResponseEntity criarSolicitacao(
            @Valid @RequestBody Solicitacao solicitacao
            ){
        return gerenteService.criarSolicitacao(solicitacao);
    }

    @GetMapping(Routes.PATH_SOLICITACOES)
    public ResponseEntity getSolicitacoes(){
        return gerenteService.getSolicitacoes();
    }




}
