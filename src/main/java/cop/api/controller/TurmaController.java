package cop.api.controller;

import cop.api.model.Aluno.DTO.DadosListagemAluno;
import cop.api.model.Turma.*;
import cop.api.model.Turma.DTO.DadosAtualizaTurma;
import cop.api.model.Turma.DTO.FiltroTurmas;
import cop.api.model.Turma.DTO.TurmaDetalhada;
import cop.api.repository.AlunoRepositoryImpl;
import cop.api.repository.TurmaRepositoryImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("sistema/turmas")
public class TurmaController {

    @Autowired
    TurmaRepositoryImpl repository;
    @Autowired
    AlunoRepositoryImpl alunoRepository;

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
        turma.atualizaInformacoes(dados);
        return ResponseEntity.ok(new TurmaDetalhada(turma));
    }

    @PostMapping("/{id}")
    public ResponseEntity<List<DadosListagemAluno>> filtroListaDeAlunosNaTurma(@PathVariable Long id, @RequestBody FiltroTurmas filtros){
        Turma turma = repository.getReferenceById(id);
        List<DadosListagemAluno> alunos = alunoRepository.findByIdNomeModalidadeStatus
                (id, filtros.getNome(), turma.getDadosTurma().getModalidade(), filtros.getStatus()).stream().map(DadosListagemAluno::new).toList();
        return ResponseEntity.ok(alunos);
    }

}
