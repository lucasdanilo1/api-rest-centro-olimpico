package cop.api.service;

import cop.api.model.Turma.DTO.DadosAtualizaTurma;
import cop.api.model.Turma.Turma;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    public void atualizaInformacoes(Turma turma, DadosAtualizaTurma dados){
        if(dados.getDadosTurma() != null){
            turma.getDadosTurma().checaCampos(dados);
        }
    }

}
