package br.com.springjwt.desafio.service;

import br.com.springjwt.desafio.dto.GerenteLoginDTO;
import br.com.springjwt.desafio.model.Agendamento;
import br.com.springjwt.desafio.model.GerenteModel;
import br.com.springjwt.desafio.repository.AgendamentoRepository;
import br.com.springjwt.desafio.repository.ClienteRepository;
import br.com.springjwt.desafio.repository.GerenteRepository;
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

    private AgendamentoRepository agendamentoRepository;
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
                findGerenteModelByEmail(gerenteLoginDTO.getEmail());

        if (gerenteModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        GerenteModel gerenteEncontrado = gerenteModelOptional.get();
        boolean passwordMatch = passwordEncoder.matches(gerenteLoginDTO.getSenha(), gerenteEncontrado.getSenha());

        HttpStatus status = (passwordMatch) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).build();
    }

    public ResponseEntity logoff() {
        //TODO: implementar logoff
        return ResponseEntity.ok().build();
    }


    public ResponseEntity saveAttendance(Agendamento agendamento) {
        if (!clienteRepository.existsByCpf(agendamento.getCliente().getCpf())) {
            clienteRepository.save(agendamento.getCliente());
        }
        agendamentoRepository.save(
                new Agendamento(null, agendamento.getDataHora(),
                        clienteRepository.getClienteByCpf(agendamento.getCliente().getCpf())));


        return ResponseEntity.ok().build();
    }

    public List<Agendamento> getAttendances() {
        return agendamentoRepository.findAll();
    }
}
