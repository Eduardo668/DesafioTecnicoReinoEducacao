package br.com.reinoeducacao.services;

import br.com.reinoeducacao.dtos.ClienteDto;
import br.com.reinoeducacao.dtos.UpdateClienteDto;
import org.springframework.stereotype.Component;

import java.util.List;

// Os metodos que possuem as regras de negocio do sistema são definidos nessa interface
public interface ClienteService {

    ClienteDto create(ClienteDto clienteDto);
    List<ClienteDto> findAll();
    ClienteDto update(Long id, UpdateClienteDto updateClienteDto);
    void delete(Long id);
    ClienteDto findById(Long id);
    ClienteDto addMiles(Long id, Long miles);
    ClienteDto reduceMiles(Long id, Long milesQuantity);

}
