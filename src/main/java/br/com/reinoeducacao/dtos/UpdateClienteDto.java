package br.com.reinoeducacao.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
