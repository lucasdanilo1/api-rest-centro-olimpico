package cop.api.Controller;

import cop.api.Model.Aluno.DTO.DadosListagemAluno;
import cop.api.Model.Aluno.Repository.AlunoRepository;
import cop.api.Model.Turma.*;
import cop.api.Model.Turma.DTO.DadosAtualizaTurma;
import cop.api.Model.Turma.DTO.FiltroTurmas;
import cop.api.Model.Turma.DTO.TurmaDetalhada;
import cop.api.Model.Turma.Repository.TurmaRepository;
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
    TurmaRepository repository;
    @Autowired
    AlunoRepository alunoRepository;

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
