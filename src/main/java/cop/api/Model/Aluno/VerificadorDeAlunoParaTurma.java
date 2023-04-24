package cop.api.Model.Aluno;

import cop.api.Model.Exceptions.IdadeIncompativelException;
import cop.api.Model.Exceptions.SexoIncompativelException;
import cop.api.Model.Exceptions.TurmaFechadaException;
import cop.api.Model.Turma.StatusTurma;
import cop.api.Model.Turma.Turma;

public class VerificadorDeAlunoParaTurma {

    public void verificarElegibilidade(Turma turma, Aluno aluno) throws IdadeIncompativelException, SexoIncompativelException, TurmaFechadaException {
            if (turma.getDadosTurma().getFaixa().permiteIdade(aluno.getDadosPessoais().getIdade())) {
            } else{
                throw new IdadeIncompativelException();
            }
            if (turma.getDadosTurma().getNaipe().name().equals(aluno.getDadosPessoais().getSexo().name()) || turma.getDadosTurma().getNaipe().name().equals("MISTO")) {
            }else{
                throw new SexoIncompativelException();
            }
            if (turma.getStatus().equals(StatusTurma.TURMA_ABERTA)) {
                aluno.turma = turma;
            } else {
                throw new TurmaFechadaException();
            }
        }
    }

