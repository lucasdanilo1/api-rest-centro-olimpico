package cop.api.service;

import cop.api.exceptions.ValidacaoAlunoParaTurmaException;
import cop.api.model.Aluno.Aluno;
import cop.api.model.Turma.Turma;
import cop.api.model.Turma.enums.StatusTurma;
import org.springframework.stereotype.Component;

@Component
public class VerificadorDeElegibilidadeDeAlunoEmTurma {
    public void verificarElegibilidade(Turma turma, Aluno aluno) throws ValidacaoAlunoParaTurmaException {
        if (turma.getDadosTurma().getFaixa().permiteIdade(aluno.getDadosPessoais().getIdade())) {
        } else{
            throw new ValidacaoAlunoParaTurmaException("Idade Incompativel.");
        }
        if (turma.getDadosTurma().getNaipe().name().equals(aluno.getDadosPessoais().getSexo().name()) || turma.getDadosTurma().getNaipe().name().equals("MISTO")) {
        } else{
            throw new ValidacaoAlunoParaTurmaException("Sexo Incompativel.");
        }
        if (turma.getStatus().equals(StatusTurma.TURMA_ABERTA)) {
        }else{
            throw new ValidacaoAlunoParaTurmaException("Turma Fechada.");
        }
    }

}
