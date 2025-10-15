package br.com.reinoeducacao.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMilesRequest {

    @NotNull
    private Long quantidade;

}
