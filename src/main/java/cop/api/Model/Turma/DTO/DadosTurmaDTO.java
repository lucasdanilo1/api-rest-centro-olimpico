package cop.api.Model.Turma.DTO;

import cop.api.Model.Turma.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosTurmaDTO {

    private Dias dias;
    private Horario horario;
    private Faixa faixa;
    private Naipe naipe;
    private Modalidade modalidade;
    private Integer quantidadeVagas = 0;

}
