package cop.api.model.Turma.DTO;

import cop.api.model.Aluno.enums.Etnia;
import cop.api.model.Aluno.enums.Sexo;
import cop.api.model.Aluno.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroAlunosNaTurma {

    String nome;
    Etnia etnia;
    Sexo sexo;
    Status status;
    String cpf;


}
