package cop.api.controller;

import cop.api.model.Aluno.DTO.AlunoDetalhado;
import cop.api.model.Aluno.DTO.DadosCadastroAluno;
import cop.api.service.CadastroAlunoService;
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
    private CadastroAlunoService service;

    @Transactional
    @PostMapping("cadastro")
    public ResponseEntity<AlunoDetalhado> cadastrarAluno(@RequestBody @Valid DadosCadastroAluno dados, UriComponentsBuilder uriBuilder) {
        var aluno = service.cadastro(dados);
        var uri = uriBuilder.path("sistema/inscritos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoDetalhado(aluno));
    }
}
