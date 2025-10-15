package br.com.reinoeducacao.controllers;

import br.com.reinoeducacao.dtos.ClienteDto;
import br.com.reinoeducacao.dtos.UpdateClienteDto;
import br.com.reinoeducacao.requests.UpdateMilesRequest;
import br.com.reinoeducacao.services.ClienteServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Nessa classe estão os endpoints da API
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteServiceImpl clienteService;

    // Endpoint POST /clientes, registra cliente no sistema
    @Operation(summary = "Criar cliente", description = "Registra um cliente no sistema")
    @PostMapping("/clientes")
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto){
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(clienteService.create(clienteDto));
    }

    // Endpoint GET /clientes, busca todos os clientes registrados
    @Operation(summary = "Buscar todos os clientes", description = "Busca todos os clientes registrados no sistema")
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDto>> findAllClientes(){
        return ResponseEntity.
                status(HttpStatus.OK)
                .body(clienteService.findAll());
    }

    // Endpoint DELETE /clientes/{clienteId}, deleta um cliente
    @Operation(summary = "Deletar cliente", description = "Deleta completamente o cliente do sistema")
    @DeleteMapping("/clientes/{clienteId}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long clienteId){
        clienteService.delete(clienteId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Cliente com ID:"+clienteId+" deletado com sucesso!");
    }

    // Endpoint GET /clientes/{clienteId}, busca um cliente atraves de seu id
    @Operation(summary = "Buscar cliente pelo ID", description = "Busca por um cliente a partir do seu ID")
    @GetMapping("/clientes/{clienteId}")
    public ResponseEntity<ClienteDto> findClienteById(@PathVariable Long clienteId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.findById(clienteId));
    }

    // Endpoint PUT /clientes/{clienteId}, atualiza os dados de um cliente
    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados do cliente")
    @PutMapping("/clientes/{clienteId}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long clienteId, @RequestBody UpdateClienteDto updateClienteDto){
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(clienteService.update(clienteId, updateClienteDto));
    }

    // Endpoint PATCH /clientes/{clienteId}/adicionar-milhas, adiciona milhas ao saldo de milhas do cliente
    // Utilizarei o metodo PATCH, pois nesse caso ao adicionar milhas estarei atualizando parcialmente os dados do cliente, ja que será somado ao valor já existente
    @Operation(summary = "Adicionar milhas", description = "Soma milhas ao saldo do cliente")
    @PatchMapping("/clientes/{clienteId}/adicionar-milhas")
    public ResponseEntity<ClienteDto> addMiles(@PathVariable Long clienteId, @RequestBody UpdateMilesRequest updateMilesRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.addMiles(clienteId, updateMilesRequest.getQuantidade()));
    }

    // Endpoint PATCH /clientes/{clienteId}/reduzir-milhas, reduz a quantidade milhas do saldo de milhas do cliente
    @Operation(summary = "Reduzir milhas", description = "Reduz milhas do saldo do cliente")
    @PatchMapping("/clientes/{clienteId}/reduzir-milhas")
    public ResponseEntity<ClienteDto> reduceMiles(@PathVariable Long clienteId, @RequestBody UpdateMilesRequest updateMilesRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteService.reduceMiles(clienteId, updateMilesRequest.getQuantidade()));
    }


}
