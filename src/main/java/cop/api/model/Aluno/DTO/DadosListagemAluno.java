package cop.api.model.Aluno.DTO;

import cop.api.model.Aluno.Aluno;
import cop.api.model.Aluno.enums.Status;
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
        this.status = aluno.getStatus();
        this.turmaId = aluno.getTurma().getId();
    }



}
