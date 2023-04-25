package cop.api.Model.Turma.DTO;

import cop.api.Model.Turma.*;
import lombok.Getter;

@Getter
public class DadosListagemTurma {

    Dias dias;
    Horario horario;
    Faixa faixa;
    Naipe naipe;
    Modalidade modalidade;

    public DadosListagemTurma(Turma turma){
        this.dias = turma.getDadosTurma().getDias();
        this.horario = turma.getDadosTurma().getHorario();
        this.faixa = turma.getDadosTurma().getFaixa();
        this.naipe = turma.getDadosTurma().getNaipe();
        this.modalidade = turma.getDadosTurma().getModalidade();
    }

}
