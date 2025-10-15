package br.com.reinoeducacao.controllers;

import br.com.reinoeducacao.dtos.ClienteDto;
import br.com.reinoeducacao.services.ClienteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteServiceImpl clienteService;

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto){
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(clienteService.create(clienteDto));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDto>> findAllClientes(){
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(clienteService.findAll());
    }

    @DeleteMapping("/clientes/{clienteId}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long clienteId){
        clienteService.delete(clienteId);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente com ID:"+clienteId+" deletado com sucesso!");
    }

}
