package br.com.reinoeducacao.services;

import br.com.reinoeducacao.dto.ClienteDto;
import br.com.reinoeducacao.dto.UpdateClienteDto;
import br.com.reinoeducacao.exceptions.ClienteException;
import br.com.reinoeducacao.models.Cliente;
import br.com.reinoeducacao.repository.ClienteRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteDto create(ClienteDto clienteDto) {
        try{

            Cliente newCliente = new Cliente();
            BeanUtils.copyProperties(clienteDto, newCliente);

            Cliente createdCliente = clienteRepository.save(newCliente);

            BeanUtils.copyProperties(createdCliente, clienteDto);

            log.info("INFO: Cliente criado com sucesso!");
            return clienteDto;

        } catch (ClienteException exception){
            throw new ClienteException("Ocorreu um erro ao tentar registrar um cliente: ", exception);
        }
    }

    @Override
    public List<ClienteDto> findAll() {
        return List.of();
    }

    @Override
    public ClienteDto update(Long id, UpdateClienteDto updateClienteDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ClienteDto findById(Long id) {
        return null;
    }

    @Override
    public ClienteDto addMiles(Long id, Long miles) {
        return null;
    }
}
