package cop.api.Model.Aluno.DTO;

import cop.api.Model.Aluno.Aluno;
import cop.api.Model.Aluno.Status;
import lombok.Getter;

@Getter
public class DadosListagemAluno {


    String dataEnvio;
    String nome;
    Status status;
    Long turmaId;

    public DadosListagemAluno(Aluno aluno){
        this.dataEnvio = aluno.getDataEnvio();
        this.nome = aluno.getDadosPessoais().getNome();
        this.status = aluno.getStatusAluno();
        this.turmaId = aluno.getTurma().getId();
    }



}
