package cop.api.model.Turma.DTO;

import cop.api.model.Turma.enums.*;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTurma(

        @NotNull
        Dias dias,
        @NotNull
        Horario horario,
        @NotNull
        Faixa faixa,
        @NotNull
        Naipe naipe,
        @NotNull
        Modalidade modalidade,
        @NotNull
        Integer quantidadeVagas

) {
}
