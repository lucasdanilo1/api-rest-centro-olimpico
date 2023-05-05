package cop.api.model.Aluno.DTO;

import cop.api.model.Aluno.Aluno;
import cop.api.model.Aluno.DadosPessoais;
import lombok.Getter;

@Getter
public class AlunoDetalhado {

    Long turmaId;
    DadosPessoais dadosPessoais;

    public AlunoDetalhado(Aluno aluno){
        this.turmaId = aluno.getTurma().getId();
        this.dadosPessoais = aluno.getDadosPessoais();
    }

}
