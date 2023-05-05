package cop.api.controller;

import cop.api.model.Aluno.Aluno;
import cop.api.model.Aluno.DTO.AlunoDetalhado;
import cop.api.model.Aluno.DTO.DadosAtualizaAluno;
import cop.api.model.Aluno.enums.Status;
import cop.api.repository.AlunoRepositoryImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sistema/inscritos")
public class AlunoController {

    @Autowired
    private AlunoRepositoryImpl repository;

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDetalhado> detalhar(@PathVariable Long id){
        Aluno aluno = repository.getReferenceById(id);
        return ResponseEntity.ok(new AlunoDetalhado(aluno));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<AlunoDetalhado> atualizar(@PathVariable Long id, @Valid @RequestBody DadosAtualizaAluno dados){
        Aluno aluno = repository.getReferenceById(id);
        aluno.atualizaInformacoes(dados);
        return ResponseEntity.ok(new AlunoDetalhado(aluno));
    }

    @Transactional
    @PutMapping("selecionar/{id}")
    public ResponseEntity selecionar(@PathVariable Long id){
        Aluno aluno = repository.getReferenceById(id);
        aluno.selecionaAluno();
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("inativar/{id}")
    public ResponseEntity InativaAtiva(@PathVariable Long id){
        Aluno aluno = repository.getReferenceById(id);
        aluno.InativaAtivaAluno();
        return ResponseEntity.noContent().build();
    }

}

