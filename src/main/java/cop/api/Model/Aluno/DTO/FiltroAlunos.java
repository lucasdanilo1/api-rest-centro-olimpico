package cop.api.Model.Aluno.DTO;

import cop.api.Model.Aluno.*;
import cop.api.Model.Turma.Modalidade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroAlunos {

    String nome;
    Etnia etnia;
    Sexo sexo;
    Modalidade modalidade;
    Status status;


}
