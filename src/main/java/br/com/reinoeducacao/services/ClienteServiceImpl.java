package br.com.reinoeducacao.services;

import br.com.reinoeducacao.dtos.ClienteDto;
import br.com.reinoeducacao.dto.UpdateClienteDto;
import br.com.reinoeducacao.exceptions.ClienteException;
import br.com.reinoeducacao.exceptions.ClienteNotFoundException;
import br.com.reinoeducacao.models.Cliente;
import br.com.reinoeducacao.repository.ClienteRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

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
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar registrar um cliente: ", exception);
        }
    }

    @Override
    public List<ClienteDto> findAll() {
        try{

            List<Cliente> clientes = clienteRepository.findAll();

            ModelMapper mapper = new ModelMapper();

            List<ClienteDto> clienteDtoList = clientes.stream()
                    .map(cliente -> mapper.map(cliente, ClienteDto.class))
                    .toList();


            return clienteDtoList;

        } catch (ClienteException exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar listar todos os clientes: ", exception);
        }
    }

    @Override
    public ClienteDto update(Long id, UpdateClienteDto updateClienteDto)     {
        try{
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (cliente.isEmpty()){
                throw new ClienteNotFoundException("WARN: O cliente com o ID:"+id+" não existe!");
            }

            modelMapper.map(updateClienteDto, cliente.get());

            Cliente updatedCliente = clienteRepository.save(cliente.get());
            ClienteDto clienteDto = new ClienteDto();

            BeanUtils.copyProperties(updatedCliente, clienteDto);

            return clienteDto;

        } catch (ClienteException exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar atualizar os dados do cliente com o ID:"+id, exception);
        }
    }

    @Override
    public void delete(Long id) {
        try{
            Optional<Cliente> cliente = clienteRepository.findById(id);

            if (cliente.isEmpty()){
                throw new ClienteNotFoundException("WARN: O cliente com o ID:"+id+" não existe!");
            }

            clienteRepository.delete(cliente.get());

        } catch (ClienteException exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar deletar o cliente com o ID:"+id+":",exception);
        }
    }

    @Override
    public ClienteDto findById(Long id) {
        try{
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (cliente.isEmpty()){
                throw new ClienteNotFoundException("WARN: O cliente com o ID:"+id+" não existe!");
            }

            ClienteDto clienteDto = new ClienteDto();

            BeanUtils.copyProperties(cliente.get(), clienteDto);

            return clienteDto;

        } catch (ClienteException exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar encontrar o cliente com o ID:"+id, exception);
        }
    }

    // Diferencial
    @Override
    public ClienteDto addMiles(Long id, Long milesQuantity) {
        try{
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (cliente.isEmpty()){
                throw new ClienteNotFoundException("WARN: O cliente com o ID:"+id+" não existe!");
            }

            // Soma a quantidade de milhas atual do cliente com a quantidade de milhas adicionadas
            cliente.get().setSaldoMilhas(cliente.get().getSaldoMilhas() + milesQuantity);

           ClienteDto clienteDto = new ClienteDto();

           Cliente updatedCliente = clienteRepository.save(cliente.get());
           BeanUtils.copyProperties(updatedCliente, clienteDto);

           return clienteDto;

        } catch (ClienteException exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar adicionar milhas ao cliente com o ID:"+id, exception);
        }
    }
}
