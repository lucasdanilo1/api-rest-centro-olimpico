package cop.api.service;

import cop.api.model.Aluno.Aluno;
import cop.api.model.Aluno.AlunoManager;
import cop.api.model.Aluno.DTO.DadosAtualizaAluno;
import cop.api.model.Turma.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    AlunoManager manager;

    public void ativa(Aluno aluno) {
        manager.ativaAluno(aluno);
    }

    public void inativa(Aluno aluno) {
        manager.inativaAluno(aluno);
    }

    public void seleciona(Aluno aluno) {
        manager.selecionaAluno(aluno);
    }

    public void matricula(Aluno aluno) {
        manager.matriculaAluno(aluno);
    }

    public void setTurma(Turma turma, Aluno aluno) {
        aluno.setTurma(turma);
    }

    public void atualizaInformacoes(Aluno aluno, DadosAtualizaAluno dados) {
        if (dados.getDadosPessoais() != null) {
            aluno.getDadosPessoais().checaCamposDadosPessoaisAtualiza(dados);
        }
    }
}
