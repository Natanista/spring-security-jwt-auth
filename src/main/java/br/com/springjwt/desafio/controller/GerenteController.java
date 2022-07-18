package br.com.springjwt.desafio.controller;

import br.com.springjwt.desafio.dto.GerenteLoginDTO;
import br.com.springjwt.desafio.model.Agendamento;
import br.com.springjwt.desafio.model.GerenteModel;
import br.com.springjwt.desafio.model.Routes;
import br.com.springjwt.desafio.service.GerenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping(Routes.PATH_LOGOFF)
    public ResponseEntity logoff(){
        return gerenteService.logoff();
    }

    @PostMapping(Routes.PATH_ATTENDANCE)
    public ResponseEntity attendace(
            @Valid @RequestBody Agendamento agendamento
            ){
        return gerenteService.saveAttendance(agendamento);
    }

    @GetMapping(Routes.PATH_ATTENDANCE)
    private ResponseEntity getAttendaces(){
        return ResponseEntity.status(HttpStatus.OK).body(gerenteService.getAttendances());
    }




}
