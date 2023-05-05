package cop.api.repository;

import cop.api.model.Turma.*;
import cop.api.model.Turma.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query("SELECT t " +
            "FROM Turma t " +
            "WHERE (:modalidade IS NULL OR t.dadosTurma.modalidade = :modalidade) " +
            "AND (:dias IS NULL OR t.dadosTurma.dias = :dias)" +
            "AND (:faixa IS NULL OR t.dadosTurma.faixa = :faixa) " +
            "AND (:horario IS NULL OR t.dadosTurma.horario = :horario) " +
            "AND (:naipe IS NULL OR t.dadosTurma.naipe = :naipe)")
    List<Turma> findByFiltros(Modalidade modalidade, Dias dias, Faixa faixa, Horario horario, Naipe naipe);

}
