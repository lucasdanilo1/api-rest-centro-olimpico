package cop.api.model.Aluno.DTO;

import cop.api.model.Aluno.enums.Etnia;
import cop.api.model.Aluno.enums.Sexo;
import cop.api.model.Aluno.enums.Status;
import cop.api.model.Turma.enums.Modalidade;
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
