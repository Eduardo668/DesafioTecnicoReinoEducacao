package br.com.reinoeducacao.services;

import br.com.reinoeducacao.dtos.ClienteDto;
import br.com.reinoeducacao.dto.UpdateClienteDto;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ClienteService {

    ClienteDto create(ClienteDto clienteDto);
    List<ClienteDto> findAll();
    ClienteDto update(Long id, UpdateClienteDto updateClienteDto);
    void delete(Long id);
    ClienteDto findById(Long id);
    ClienteDto addMiles(Long id, Long miles);
    ClienteDto reduceMiles(Long id, Long milesQuantity);

}
