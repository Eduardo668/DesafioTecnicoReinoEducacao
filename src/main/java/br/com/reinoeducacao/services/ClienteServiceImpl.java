package br.com.reinoeducacao.services;

import br.com.reinoeducacao.dtos.ClienteDto;
import br.com.reinoeducacao.dto.UpdateClienteDto;
import br.com.reinoeducacao.exceptions.ClienteException;
import br.com.reinoeducacao.exceptions.ClienteNotFoundException;
import br.com.reinoeducacao.exceptions.InsufficientMilesException;
import br.com.reinoeducacao.models.Cliente;
import br.com.reinoeducacao.repository.ClienteRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Nessa classe está presente toda a regra de negocio do sistema
@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    // Metodo para criar um cliente
    @Transactional
    @Override
    public ClienteDto create(ClienteDto clienteDto) {
        try{

            Cliente newCliente = new Cliente();
            BeanUtils.copyProperties(clienteDto, newCliente);

            Cliente createdCliente = clienteRepository.save(newCliente);

            BeanUtils.copyProperties(createdCliente, clienteDto);

            log.info("INFO: Cliente criado com sucesso!");
            return clienteDto;

        } catch (Exception exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar registrar um cliente, verifique os logs para mais detalhes: ", exception);
        }
    }

    // Metodo para buscar todos os clientes registrados no sistema
    @Override
    public List<ClienteDto> findAll() {
        try{

            List<Cliente> clientes = clienteRepository.findAll();

            ModelMapper mapper = new ModelMapper();

            List<ClienteDto> clienteDtoList = clientes.stream()
                    .map(cliente -> mapper.map(cliente, ClienteDto.class))
                    .toList();


            return clienteDtoList;

        } catch (Exception exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar listar todos os clientes, verifique os logs para mais detalhes: ", exception);
        }
    }

    // Metodo para atualizar os dados de um cliente
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

        } catch (Exception exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar atualizar os dados do cliente com o ID, verifique os logs para mais detalhes:"+id, exception);
        }
    }

    // Metodo para deletar um cliente
    @Override
    public void delete(Long id) {
        try{
            Optional<Cliente> cliente = clienteRepository.findById(id);

            if (cliente.isEmpty()){
                throw new ClienteNotFoundException("WARN: O cliente com o ID:"+id+" não existe!");
            }

            clienteRepository.delete(cliente.get());

        } catch (Exception exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar deletar o cliente com o ID:"+id+", verifique os logs para mais detalhes: ",exception);
        }
    }

    // Metodo para buscar um cliente pelo seu id
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

        } catch (Exception exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar encontrar o cliente com o ID:"+id+", verifique os logs para mais detalhes: ", exception);
        }
    }

    // Metodo para adicionar milhas ao saldo de milhas do cliente
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

        } catch (Exception exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar adicionar milhas ao cliente com o ID:"+id+", verifique os logs para mais detalhes: ", exception);
        }
    }

    // Metodo para reduzir milhas do saldo de milhas do cliente
    @Override
    public ClienteDto reduceMiles(Long id, Long milesQuantity) {
        try{
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (cliente.isEmpty()){
                throw new ClienteNotFoundException("WARN: O cliente com o ID:"+id+" não existe!");
            }

            if (cliente.get().getSaldoMilhas() > 0 && (cliente.get().getSaldoMilhas() - milesQuantity) >= 0) {
                cliente.get().setSaldoMilhas(cliente.get().getSaldoMilhas() - milesQuantity);
            } else {
                throw new InsufficientMilesException(
                        "WARN: A quantidade de milhas a reduzir é maior que o saldo atual do cliente."
                );

            }

            ClienteDto clienteDto = new ClienteDto();

            Cliente updatedCliente = clienteRepository.save(cliente.get());
            BeanUtils.copyProperties(updatedCliente, clienteDto);

            return clienteDto;

        } catch (Exception exception){
            throw new ClienteException("ERROR: Ocorreu um erro ao tentar reduzir as milhas do cliente com o ID:"+id+", verifique os logs para mais detalhes:", exception);
        }
    }
}
