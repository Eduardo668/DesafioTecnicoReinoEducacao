package br.com.reinoeducacao.controllers;

import br.com.reinoeducacao.dtos.ClienteDto;
import br.com.reinoeducacao.requests.AddMilesRequest;
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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Cliente com ID:"+clienteId+" deletado com sucesso!");
    }

    @GetMapping("/clientes/{clienteId}")
    public ResponseEntity<ClienteDto> findClienteById(@PathVariable Long clienteId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.findById(clienteId));
    }

    @PutMapping("/clientes/{clienteId}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long clienteId, @RequestBody br.com.reinoeducacao.dto.UpdateClienteDto updateClienteDto){
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(clienteService.update(clienteId, updateClienteDto));
    }

    // Utilizarei o metodo PATCH, pois nesse caso ao adicionar milhas estarei atualizando parcialmente os dados do cliente, ja que será somado ao valor já existente
    @PatchMapping("/clientes/{clienteId}/adicionar-milhas")
    public ResponseEntity<ClienteDto> addMiles(@PathVariable Long clienteId, @RequestBody AddMilesRequest addMilesRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.addMiles(clienteId, addMilesRequest.getQuantidade()));
    }


}
