package cop.api.util;

import cop.api.exceptions.IdadeIncompativelException;
import cop.api.exceptions.SexoIncompativelException;
import cop.api.exceptions.TurmaFechadaException;
import cop.api.model.Aluno.Aluno;
import cop.api.model.Turma.enums.StatusTurma;
import cop.api.model.Turma.Turma;

public class VerificadorDeAlunoParaTurma {

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
                aluno.setTurma(turma);
            } else {
                throw new TurmaFechadaException();
            }
        }
    }

