package cop.api.Model.Aluno;

import cop.api.Model.Exceptions.StatusAlunoException;
import cop.api.Model.Turma.Turma;

public class AlunoManager {

    private VerificadorDeAlunoParaTurma verificaAluno = new VerificadorDeAlunoParaTurma();

    public void inativaAluno(Aluno aluno){
        if(aluno.getStatusAluno().equals(Status.SELECIONADO) || aluno.getStatusAluno().equals(Status.MATRICULADO)){
            aluno.turmaAtual().getMatriculados().remove(aluno);
            aluno.turmaAtual().getInscritos().add(aluno);
            aluno.setStatusAluno(Status.INATIVADO);
            aluno.ativo = false;
        }else{
            aluno.setStatusAluno(Status.INATIVADO);
            aluno.ativo = false;
        }
    }

    public void ativaAluno(Aluno aluno){
        if(aluno.getStatusAluno() != Status.INATIVADO){
            throw new StatusAlunoException("Aluno deve estar Inativado");
        }
        aluno.setStatusAluno(Status.INSCRITO);
        aluno.setAtivo(true);
    }

    public void selecionaAluno(Aluno aluno){
        if(aluno.getStatusAluno() != Status.INSCRITO){
            throw new StatusAlunoException("Aluno deve estar Inscrito");
        }
        aluno.turmaAtual().getInscritos().remove(aluno);
        aluno.turmaAtual().getMatriculados().add(aluno);
        aluno.setStatusAluno(Status.SELECIONADO);
    }

    public void matriculaAluno(Aluno aluno) {
        if (aluno.getStatusAluno() != Status.SELECIONADO) {
            throw new StatusAlunoException("Aluno deve estar Selecionado");
        }
        aluno.setStatusAluno(Status.MATRICULADO);
    }

    public void alteraTurma(Turma novaTurma, Aluno aluno){

        verificaAluno.verificarElegibilidade(novaTurma, aluno);

        if(aluno.getStatusAluno().equals(Status.INSCRITO) || aluno.getStatusAluno().equals(Status.INATIVADO)){
            aluno.turmaAtual().getInscritos().remove(aluno);
        }else{
            aluno.turmaAtual().getMatriculados().remove(aluno);
        }
        aluno.setStatusAluno(Status.INSCRITO);
        novaTurma.getInscritos().add(aluno);
    }



}
