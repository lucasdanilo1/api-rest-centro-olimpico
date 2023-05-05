package cop.api.repository;

import cop.api.model.Turma.Turma;
import cop.api.model.Turma.enums.*;

import java.util.List;

public interface TurmaRepositoryImpl extends TurmaRepository{

    List<Turma> findByFiltros(Modalidade modalidade, Dias dias, Faixa faixa, Horario horario, Naipe naipe);

}
