package cop.api.Model.Turma.DTO;

import cop.api.Model.Aluno.Aluno;
import cop.api.Model.Aluno.DTO.AlunoDetalhado;
import cop.api.Model.Aluno.DTO.DadosListagemAluno;
import cop.api.Model.Aluno.Repository.AlunoRepository;
import cop.api.Model.Turma.DadosTurma;
import cop.api.Model.Turma.Repository.TurmaRepository;
import cop.api.Model.Turma.StatusTurma;
import cop.api.Model.Turma.Turma;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

}
