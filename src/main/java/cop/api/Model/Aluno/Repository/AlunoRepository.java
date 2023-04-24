package cop.api.Model.Aluno.Repository;

import cop.api.Model.Aluno.*;
import cop.api.Model.Aluno.Aluno;
import cop.api.Model.Turma.Modalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByDadosPessoaisCpf(String cpf);

    @Query("SELECT a FROM Aluno a WHERE a.turma.id = :id")
    List<Aluno> findAllByTurmaId(Long id);

    @Query("SELECT a FROM Aluno a WHERE a.dadosPessoais.nome like %?1%")
    List<Aluno> findByNome(String nome);

    @Query("SELECT a FROM Aluno a WHERE (:id IS NULL OR a.turma.id = :id) AND (:nome IS NULL OR a.dadosPessoais.nome LIKE %:nome%) AND (:modalidade IS NULL OR a.turma.dadosTurma.modalidade = :modalidade) AND (:status IS NULL OR a.statusAluno = :status)")
    List<Aluno> findByIdNomeModalidadeStatus(Long id, String nome, Modalidade modalidade, Status status);

    @Query("SELECT a FROM Aluno a WHERE (:nome IS NULL OR a.dadosPessoais.nome LIKE %:nome%) AND (:modalidade IS NULL OR a.turma.dadosTurma.modalidade = :modalidade) AND (:status IS NULL OR a.statusAluno = :status) AND (:etnia IS NULL OR a.dadosPessoais.etnia = :etnia) AND (:sexo IS NULL OR a.dadosPessoais.sexo = :sexo)")
    List<Aluno> findByTodosFiltros(String nome, Modalidade modalidade, Status status, Etnia etnia, Sexo sexo);

}
