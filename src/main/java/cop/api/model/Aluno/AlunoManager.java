package cop.api.model.Aluno;

import cop.api.exceptions.StatusAlunoException;
import cop.api.model.Aluno.enums.Status;
import cop.api.model.Turma.Turma;
import org.springframework.stereotype.Component;

@Component
public class AlunoManager {

    public void inativaAluno(Aluno aluno){
        if(aluno.getStatus().equals(Status.SELECIONADO) || aluno.getStatus().equals(Status.MATRICULADO)){
            aluno.getTurma().getMatriculados().remove(aluno);
            aluno.getTurma().getInscritos().add(aluno);
            aluno.setStatus(Status.INATIVADO);
            aluno.setAtivo(false);
        }else{
            aluno.setStatus(Status.INATIVADO);
            aluno.setAtivo(false);
        }
    }

    public void ativaAluno(Aluno aluno){
        if(aluno.getStatus() != Status.INATIVADO){
            throw new StatusAlunoException("Aluno deve estar Inativado");
        }
        aluno.setStatus(Status.INSCRITO);
        aluno.setAtivo(true);
    }

    public void selecionaAluno(Aluno aluno){
        if(aluno.getStatus() != Status.INSCRITO){
            throw new StatusAlunoException("Aluno deve estar Inscrito");
        }
        aluno.getTurma().getInscritos().remove(aluno);
        aluno.getTurma().getMatriculados().add(aluno);
        aluno.setStatus(Status.SELECIONADO);
    }

    public void matriculaAluno(Aluno aluno) {
        if (aluno.getStatus() != Status.SELECIONADO) {
            throw new StatusAlunoException("Aluno deve estar Selecionado");
        }
        aluno.setStatus(Status.MATRICULADO);
    }

    public void alteraTurma(Turma turma, Aluno aluno){
        if(aluno.getStatus().equals(Status.INSCRITO) || aluno.getStatus().equals(Status.INATIVADO)){
            if(aluno.getTurma() != null){
                aluno.getTurma().getInscritos().remove(aluno);
            }
        }else{
            aluno.getTurma().getMatriculados().remove(aluno);
        }
        aluno.setTurma(turma);
        aluno.setStatus(Status.INSCRITO);
        turma.getInscritos().add(aluno);
    }
}
