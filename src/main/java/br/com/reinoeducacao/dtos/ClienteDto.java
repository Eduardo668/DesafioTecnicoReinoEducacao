package br.com.reinoeducacao.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/* Esta é classe de Objeto de transferencia de dados relativa a classe Cliente, ela serve para que
possamos receber e enviar os dados do Cliente, sem expor o funcionamento e os atributos internos da classe Cliente principal*/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
