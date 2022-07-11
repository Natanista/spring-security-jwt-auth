package com.example.banksystemjwt.service;


import com.example.banksystemjwt.dto.GerenteLoginDTO;
import com.example.banksystemjwt.model.Solicitacao;
import com.example.banksystemjwt.model.GerenteModel;
import com.example.banksystemjwt.repository.SolicitacaoRepository;
import com.example.banksystemjwt.repository.GerenteRepository;
import com.example.banksystemjwt.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GerenteService {
    private GerenteRepository gerenteRepository;

    private SolicitacaoRepository solicitacaoRepository;
    private ClienteRepository clienteRepository;

    private PasswordEncoder passwordEncoder;


    public ResponseEntity signup(GerenteModel gerenteModel) {
        if (!gerenteModel.getSenha().equals(gerenteModel.getConfirmacaoSenha())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As senhas nao conferem!");
        }
        if (gerenteRepository.existsByEmail(gerenteModel.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nao e possivel criar uma conta com esse email.");
        }
        if (gerenteRepository.existsByCpf(gerenteModel.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nao e possivel criar uma conta com esse cpf.");
        }
        gerenteModel.setSenha(passwordEncoder.encode(gerenteModel.getSenha()));
        gerenteModel.setConfirmacaoSenha(passwordEncoder.encode(gerenteModel.getSenha()));
        gerenteRepository.save(gerenteModel);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity login(GerenteLoginDTO gerenteLoginDTO) {
        Optional<GerenteModel> gerenteModelOptional = gerenteRepository.
                findGerenteByEmail(gerenteLoginDTO.getEmail());

        if (gerenteModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        GerenteModel gerenteEncontrado = gerenteModelOptional.get();
        boolean passwordMatch = passwordEncoder.matches(gerenteLoginDTO.getSenha(), gerenteEncontrado.getSenha());

        HttpStatus status = (passwordMatch) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).build();
    }

    public ResponseEntity criarSolicitacao(Solicitacao solicitacao) {
        if (!clienteRepository.existsByCpf(solicitacao.getCliente().getCpf())) {
            clienteRepository.save(solicitacao.getCliente());
        }
        solicitacaoRepository.save(
                new Solicitacao(null, solicitacao.getDataHora(),
                        clienteRepository.getClienteByCpf(solicitacao.getCliente().getCpf())));


        return ResponseEntity.ok().build();
    }

    public ResponseEntity getSolicitacoes(){
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findAll();
       if(solicitacaoList.isEmpty()
       ){
           return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       }

       return ResponseEntity.status(HttpStatus.OK).body(solicitacaoList);
    }

}
