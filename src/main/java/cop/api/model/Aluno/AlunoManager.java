package cop.api.model.Aluno;

import cop.api.exceptions.IdadeIncompativelException;
import cop.api.exceptions.SexoIncompativelException;
import cop.api.exceptions.StatusAlunoException;
import cop.api.exceptions.TurmaFechadaException;
import cop.api.model.Aluno.enums.Status;
import cop.api.model.Turma.Turma;
import cop.api.model.Turma.enums.StatusTurma;

public class AlunoManager {

    //verifica elegibilidade de aluno na nova turma
    public void verificarElegibilidade(Turma turma, Aluno aluno) throws IdadeIncompativelException, SexoIncompativelException, TurmaFechadaException {
        if (turma.getDadosTurma().getFaixa().permiteIdade(aluno.getDadosPessoais().getIdade())) {
        } else{
            throw new IdadeIncompativelException();
        }
        if (turma.getDadosTurma().getNaipe().name().equals(aluno.getDadosPessoais().getSexo().name()) || turma.getDadosTurma().getNaipe().name().equals("MISTO")) {
        } else{
            throw new SexoIncompativelException();
        }
        if (turma.getStatus().equals(StatusTurma.TURMA_ABERTA)) {
            alteraTurma(turma, aluno);
        } else {
            throw new TurmaFechadaException();
        }
    }

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

    public void alteraTurma(Turma novaTurma, Aluno aluno){

        verificarElegibilidade(novaTurma, aluno);

        if(aluno.getStatus().equals(Status.INSCRITO) || aluno.getStatus().equals(Status.INATIVADO)){
            aluno.getTurma().getInscritos().remove(aluno);
        }else{
            aluno.getTurma().getMatriculados().remove(aluno);
        }
        aluno.setStatus(Status.INSCRITO);
        novaTurma.getInscritos().add(aluno);
    }
}
