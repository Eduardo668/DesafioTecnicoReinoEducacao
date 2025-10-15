package br.com.reinoeducacao.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

// Esta é a entidade base do Sistema
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente_table")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
