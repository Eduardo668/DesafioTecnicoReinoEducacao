package br.com.reinoeducacao.dtos;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*Esta é a classe de Objeto de transferencia de dados relativa a classe Cliente, porem
* essa versão é utilizada para receber os dados que serão utilizados para atualizar o Cliente*/
@Getter
@Setter
@Builder
public class UpdateClienteDto {

    @Size(max = 200)
    private String nome;

    @Size(max = 100)
    private String email;

    @Size(max = 100)
    private String cartao;

    private Long saldoMilhas;

    @Size(max = 400)
    private String destinoDesejado;

}
