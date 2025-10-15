package br.com.reinoeducacao.repository;

import br.com.reinoeducacao.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Esta interface é responsavel pela camada de persistencia ao banco de dados
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
