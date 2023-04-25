package cop.api.Controller;

import cop.api.Model.Aluno.DTO.DadosListagemAluno;
import cop.api.Model.Aluno.DTO.FiltroAlunos;
import cop.api.Model.Aluno.Repository.AlunoRepository;
import cop.api.Model.Turma.DTO.DadosCadastroTurma;
import cop.api.Model.Turma.DTO.DadosListagemTurma;
import cop.api.Model.Turma.DTO.FiltroTurmas;
import cop.api.Model.Turma.Repository.TurmaRepository;
import cop.api.Model.Turma.Turma;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sistema")
public class SistemaController {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    TurmaRepository turmaRepository;

    @Transactional
    @PostMapping("criarTurma")
    public ResponseEntity cadastroTurma(@RequestBody @Valid DadosCadastroTurma dados) {
        Turma turma = new Turma(dados);
        turmaRepository.save(turma);
        return ResponseEntity.ok("");
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
        var alunos = alunoRepository.findByTodosFiltros(dados.getNome(), dados.getModalidade(), dados.getStatus(), dados.getEtnia(), dados.getSexo()).stream().map(DadosListagemAluno::new).toList();
        return ResponseEntity.ok(alunos);
    }

}
