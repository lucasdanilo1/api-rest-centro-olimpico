package cop.api.Model.Turma.DTO;

import cop.api.Model.Aluno.Status;
import cop.api.Model.Turma.*;
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
