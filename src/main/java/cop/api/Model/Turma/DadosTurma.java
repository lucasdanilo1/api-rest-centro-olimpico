package cop.api.Model.Turma;

import cop.api.Model.Turma.DTO.DadosAtualizaTurma;
import cop.api.Model.Turma.DTO.DadosCadastroTurma;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class DadosTurma {

    @Enumerated(EnumType.STRING)
    private Dias dias;
    @Enumerated(EnumType.STRING)
    private Horario horario;
    @Enumerated(EnumType.STRING)
    private Faixa faixa;
    @Enumerated(EnumType.STRING)
    private Naipe naipe;
    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;
    private Integer quantidadeVagas = 0;

    public DadosTurma(DadosCadastroTurma dados){
        this.dias = dados.dias();
        this.horario = dados.horario();
        this.faixa = dados.faixa();
        this.naipe = dados.naipe();
        this.modalidade = dados.modalidade();
        this.quantidadeVagas = dados.quantidadeVagas();
    }

    public void checaCampos(DadosAtualizaTurma dados){
        if(dados.getDadosTurma().getDias() != null){
            this.dias = dados.getDadosTurma().getDias();
        }
        if(dados.getDadosTurma().getHorario() != null){
            this.horario = dados.getDadosTurma().getHorario();
        }
        if(dados.getDadosTurma().getFaixa() != null){
            this.faixa = dados.getDadosTurma().getFaixa();
        }
        if(dados.getDadosTurma().getNaipe() != null){
            this.naipe = dados.getDadosTurma().getNaipe();
        }
        if(dados.getDadosTurma().getModalidade() != null){
            this.modalidade = dados.getDadosTurma().getModalidade();
        }
    }

}
