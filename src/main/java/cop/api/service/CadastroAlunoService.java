package cop.api.service;

import cop.api.exceptions.AlunoJaCadastradoException;
import cop.api.model.Aluno.Aluno;
import cop.api.model.Aluno.DTO.DadosCadastroAluno;
import cop.api.model.Turma.Turma;
import cop.api.repository.AlunoRepository;
import cop.api.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroAlunoService {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    TurmaRepository turmaRepository;
    @Autowired
    AlunoService service;

    public Aluno cadastro(DadosCadastroAluno dados) {
        if(!turmaRepository.existsById(dados.getTurmaId())){
            throw new RuntimeException("Id da turma não existe");
        }
        if (alunoRepository.existsByDadosPessoaisCpf(dados.getDadosPessoais().getCpf())) {
            throw new AlunoJaCadastradoException();
        }
        Aluno aluno = new Aluno(dados);
        Turma turma = turmaRepository.findById(dados.getTurmaId()).get();
        service.setTurma(turma, aluno);
        alunoRepository.save(aluno);
        return aluno;
    }
}
