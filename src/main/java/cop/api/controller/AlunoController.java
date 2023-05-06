package cop.api.controller;

import cop.api.model.Aluno.Aluno;
import cop.api.model.Aluno.DTO.AlunoDetalhado;
import cop.api.model.Aluno.DTO.DadosAtualizaAluno;
import cop.api.repository.AlunoRepositoryImpl;
import cop.api.service.AlunoService;
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
    @Autowired
    private AlunoService service;

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDetalhado> detalhar(@PathVariable Long id){
        Aluno aluno = repository.getReferenceById(id);
        return ResponseEntity.ok(new AlunoDetalhado(aluno));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<AlunoDetalhado> atualizar(@PathVariable Long id, @Valid @RequestBody DadosAtualizaAluno dados){
        Aluno aluno = repository.getReferenceById(id);
        service.atualizaInformacoes(aluno, dados);
        return ResponseEntity.ok(new AlunoDetalhado(aluno));
    }

    @Transactional
    @PutMapping("selecionar/{id}")
    public ResponseEntity selecionar(@PathVariable Long id){
        Aluno aluno = repository.getReferenceById(id);
        service.seleciona(aluno);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("ativar/{id}")
    public ResponseEntity ativa(@PathVariable Long id){
        Aluno aluno = repository.getReferenceById(id);
        service.ativa(aluno);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("inativar/{id}")
    public ResponseEntity inativa(@PathVariable Long id){
        Aluno aluno = repository.getReferenceById(id);
        service.inativa(aluno);
        return ResponseEntity.noContent().build();
    }

}

