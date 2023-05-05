package cop.api.model.Turma.DTO;

import cop.api.model.Aluno.enums.Status;
import cop.api.model.Turma.enums.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroTurmas {

    String nome;
    Status status;
    Modalidade modalidade;
    Horario horario;
    Faixa faixa;
    Dias dias;
    Naipe naipe;

}
