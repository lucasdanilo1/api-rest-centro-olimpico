package cop.api.model.Turma.DTO;

import cop.api.model.Turma.enums.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DadosTurmaDTO {

    private Dias dias;
    private Horario horario;
    private Faixa faixa;
    private Naipe naipe;
    private Modalidade modalidade;
    private Integer quantidadeVagas;

}
