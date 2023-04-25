package cop.api.Model.Aluno.DTO;

import cop.api.Model.Aluno.Aluno;
import cop.api.Model.Aluno.DadosPessoais;

public class AlunoDetalhado {

    Long turmaId;
    DadosPessoais dadosPessoais;

    public AlunoDetalhado(Aluno aluno){
        this.turmaId = aluno.getTurma().getId();
        this.dadosPessoais = aluno.getDadosPessoais();
    }

}
