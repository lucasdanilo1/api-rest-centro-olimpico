package cop.api.Controller;

import cop.api.Model.Aluno.Aluno;
import cop.api.Model.Aluno.Repository.AlunoRepository;
import cop.api.Model.Turma.*;
import cop.api.Model.Turma.DTO.DadosAtualizaTurma;
import cop.api.Model.Turma.DTO.FiltroTurmas;
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
    public ResponseEntity turma(@PathVariable Long id){
        Turma turma = repository.getReferenceById(id);
        List<Aluno> alunos = alunoRepository.findAllByTurmaId(id);
        return ResponseEntity.ok("");
    }

    @PostMapping("/{id}")
    public ResponseEntity alunosFiltrados(@PathVariable Long id, @RequestBody FiltroTurmas filtros){
        Turma turma = repository.getReferenceById(id);
        List<Aluno> alunos = alunoRepository.findByIdNomeModalidadeStatus(id, filtros.getNome(), turma.getDadosTurma().getModalidade(), filtros.getStatus());
        return ResponseEntity.ok("");
    }

    @Transactional
    @PutMapping("edit/{id}")
    public ResponseEntity editarTurma(@PathVariable Long id, @RequestBody @Valid DadosAtualizaTurma dados){
        Turma turma = repository.getReferenceById(id);
        turma.atualizaInformacoes(dados);
        return ResponseEntity.ok("");
    }

}
