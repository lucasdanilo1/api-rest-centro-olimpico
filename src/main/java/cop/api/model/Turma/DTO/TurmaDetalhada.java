package cop.api.model.Turma.DTO;

import cop.api.model.Aluno.DTO.DadosListagemAluno;
import cop.api.model.Turma.DadosTurma;
import cop.api.model.Turma.enums.StatusTurma;
import cop.api.model.Turma.Turma;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TurmaDetalhada {

    private Long id;
    private String nome;
    private StatusTurma status;
    private DadosTurma dadosTurma;
    private List<DadosListagemAluno> inscritos;

    public TurmaDetalhada(Turma turma, List<DadosListagemAluno> inscritos){
        this.id = turma.getId();
        this.nome = turma.getNome();
        this.dadosTurma = turma.getDadosTurma();
        this.status = turma.getStatus();
        this.inscritos = inscritos;
    }

    public TurmaDetalhada(Turma turma){
        this.id = turma.getId();
        this.nome = turma.getNome();
        this.dadosTurma = turma.getDadosTurma();
        this.status = turma.getStatus();
    }

}
