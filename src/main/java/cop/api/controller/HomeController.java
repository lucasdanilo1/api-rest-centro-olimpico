package cop.api.controller;

import cop.api.model.Aluno.Aluno;
import cop.api.model.Aluno.DTO.AlunoDetalhado;
import cop.api.model.Aluno.DTO.DadosCadastroAluno;
import cop.api.exceptions.AlunoJaCadastradoException;
import cop.api.repository.AlunoRepositoryImpl;
import cop.api.model.Turma.Turma;
import cop.api.repository.TurmaRepositoryImpl;
import cop.api.service.AlunoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("sigecop")
public class HomeController {

    @Autowired
    private AlunoRepositoryImpl alunoRepository;
    @Autowired
    private TurmaRepositoryImpl turmaRepository;
    @Autowired
    private AlunoService service;

    @Transactional
    @PostMapping("cadastro")
    public ResponseEntity cadastrarAluno(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder) throws Exception {
        if(alunoRepository.existsByDadosPessoaisCpf(dados.getDadosPessoais().getCpf())){
            throw new AlunoJaCadastradoException();
        }else {
            Turma turma = turmaRepository.findById(dados.getTurmaId()).orElseThrow(() -> new Exception("Turma não encontrada"));
            Aluno aluno = new Aluno(dados);
            service.alteraTurma(turma, aluno);
            alunoRepository.save(aluno);

            var uri = uriBuilder.path("sistema/inscritos/{id}").buildAndExpand(aluno.getId()).toUri();
            return ResponseEntity.created(uri).body(new AlunoDetalhado(aluno));
        }
    }
}
