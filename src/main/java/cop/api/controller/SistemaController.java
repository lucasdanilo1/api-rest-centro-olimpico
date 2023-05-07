package cop.api.controller;

import cop.api.model.Aluno.DTO.DadosListagemAluno;
import cop.api.model.Aluno.DTO.FiltroAlunos;
import cop.api.model.Turma.DTO.DadosCadastroTurma;
import cop.api.model.Turma.DTO.DadosListagemTurma;
import cop.api.model.Turma.DTO.FiltroTurmas;
import cop.api.model.Turma.DTO.TurmaDetalhada;
import cop.api.repository.AlunoRepository;
import cop.api.repository.TurmaRepository;
import cop.api.model.Turma.Turma;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("sistema")
@SecurityRequirement(name = "bearer-key")
public class SistemaController {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    TurmaRepository turmaRepository;

    @Transactional
    @PostMapping("novaTurma")
    public ResponseEntity<TurmaDetalhada> novaTurma(@RequestBody @Valid DadosCadastroTurma dados, UriComponentsBuilder uriBuilder) {
        Turma turma = new Turma(dados);
        turmaRepository.save(turma);
        var uri = uriBuilder.path("sistema/turmas/{id}").buildAndExpand(turma.getId()).toUri();
        return ResponseEntity.created(uri).body(new TurmaDetalhada(turma));
    }

    @GetMapping("listaTurmas")
    public ResponseEntity<List<DadosListagemTurma>> listaTurma(Pageable paginacao) {
        var page = turmaRepository.findAll(paginacao).map(DadosListagemTurma::new).toList();
        return ResponseEntity.ok(page);
    }

    @PostMapping("listaTurmas")
    public ResponseEntity<List<DadosListagemTurma>> listaTurmasFiltrada(@RequestBody FiltroTurmas filtros){
        var turmas = turmaRepository.findByFiltros(filtros.getModalidade(), filtros.getDias(), filtros.getFaixa(), filtros.getHorario(), filtros.getNaipe()).stream().map(DadosListagemTurma::new).toList();
        return ResponseEntity.ok(turmas);
    }

    @GetMapping("listaInscritos")
    public ResponseEntity<List<DadosListagemAluno>> listaInscritos(Pageable paginacao) {
        var page = alunoRepository.findAll(paginacao).map(DadosListagemAluno::new).toList();
        return ResponseEntity.ok(page);
    }

    @PostMapping("listaInscritos")
    public ResponseEntity<List<DadosListagemAluno>> listaInscritosFiltrada(@RequestBody FiltroAlunos dados){
        var alunos = alunoRepository.findByFiltros(dados.getNome(), dados.getModalidade(), dados.getStatus(), dados.getEtnia(), dados.getSexo(), dados.getCpf())
                .stream().map(DadosListagemAluno::new).toList();
        return ResponseEntity.ok(alunos);
    }

}
