package cop.api.Controller;

import cop.api.Model.Aluno.Aluno;
import cop.api.Model.Aluno.DTO.AlunoDetalhado;
import cop.api.Model.Aluno.DTO.DadosAtualizaAluno;
import cop.api.Model.Aluno.Repository.AlunoRepository;
import cop.api.Model.Aluno.Status;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sistema/inscritos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

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
        if(aluno.getStatusAluno() == Status.INATIVADO){
            aluno.ativaAluno();
        }else{
            aluno.inativaAluno();
        }
        return ResponseEntity.noContent().build();
    }

}

