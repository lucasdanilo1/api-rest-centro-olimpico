package cop.api.repository;

import cop.api.model.Aluno.Aluno;
import cop.api.model.Aluno.enums.Etnia;
import cop.api.model.Aluno.enums.Sexo;
import cop.api.model.Aluno.enums.Status;
import cop.api.model.Turma.enums.Modalidade;

import java.util.List;

public interface AlunoRepositoryImpl extends AlunoRepository{


    @Override
    public boolean existsByDadosPessoaisCpf(String cpf);

    @Override
    public List<Aluno> findAllByTurmaId(Long id);

    @Override
    public List<Aluno> findByNome(String nome);

    @Override
    public List<Aluno> findByIdNomeModalidadeStatus(Long id, String nome, Modalidade modalidade, Status status);

    @Override
    public List<Aluno> findByTodosFiltros(String nome, Modalidade modalidade, Status status, Etnia etnia, Sexo sexo);

}
