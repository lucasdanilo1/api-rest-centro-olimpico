package cop.api.controller;

import cop.api.model.Aluno.DTO.DadosListagemAluno;
import cop.api.model.Turma.*;
import cop.api.model.Turma.DTO.DadosAtualizaTurma;
import cop.api.model.Turma.DTO.FiltroAlunosNaTurma;
import cop.api.model.Turma.DTO.TurmaDetalhada;
import cop.api.repository.AlunoRepository;
import cop.api.repository.TurmaRepository;
import cop.api.service.TurmaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("sistema/turmas")
@SecurityRequirement(name = "bearer-key")
public class TurmaController {

    @Autowired
    TurmaRepository repository;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    TurmaService service;

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDetalhada> detalhar(@PathVariable Long id){
        Turma turma = repository.getReferenceById(id);
        List<DadosListagemAluno> inscritos = alunoRepository.findAllByTurmaId(id).stream().map(DadosListagemAluno::new).toList();
        return ResponseEntity.ok(new TurmaDetalhada(turma, inscritos));
    }

    @Transactional
    @PutMapping("edit/{id}")
    public ResponseEntity<TurmaDetalhada> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizaTurma dados){
        Turma turma = repository.getReferenceById(id);
        service.atualizaInformacoes(turma, dados);
        return ResponseEntity.ok(new TurmaDetalhada(turma));
    }

    @PostMapping("/{id}")
    public ResponseEntity<List<DadosListagemAluno>> filtroListaDeAlunosNaTurma(@PathVariable Long id, @RequestBody FiltroAlunosNaTurma filtros){
        List<DadosListagemAluno> alunos = alunoRepository.findByFiltrosNaTurma
                (id, filtros.getNome(), filtros.getEtnia(), filtros.getSexo(), filtros.getStatus(), filtros.getCpf())
                .stream().map(DadosListagemAluno::new).toList();
        return ResponseEntity.ok(alunos);
    }

}
