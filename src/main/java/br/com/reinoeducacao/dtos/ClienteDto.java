package br.com.reinoeducacao.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClienteDto {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String nome;

    @NotBlank
    @Column(unique = true)
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 100)
    private String cartao;

    @NotNull
    private Long saldoMilhas;

    @NotBlank
    @Size(max = 400)
    private String destinoDesejado;
    

}
