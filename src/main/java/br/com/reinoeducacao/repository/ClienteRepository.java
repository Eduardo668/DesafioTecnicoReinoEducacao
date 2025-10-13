package br.com.reinoeducacao.repository;

import br.com.reinoeducacao.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Busca cliente pelo email
    Cliente findByEmail(String email);


}
