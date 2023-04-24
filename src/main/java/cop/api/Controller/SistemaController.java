package cop.api.Controller;

import cop.api.Model.Aluno.Aluno;
import cop.api.Model.Aluno.DTO.FiltroAlunos;
import cop.api.Model.Aluno.Repository.AlunoRepository;
import cop.api.Model.Turma.DTO.DadosCadastroTurma;
import cop.api.Model.Turma.DTO.FiltroTurmas;
import cop.api.Model.Turma.Repository.TurmaRepository;
import cop.api.Model.Turma.Turma;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity listaTurma() {
        List<Turma> turmas = turmaRepository.findAll();
        return ResponseEntity.ok("");
    }

    @PostMapping("listaTurmas")
    public ResponseEntity listaTurmasFiltrada(FiltroTurmas filtros){
        List<Turma> turmas = turmaRepository.findByFiltros(filtros.getModalidade(), filtros.getDias(), filtros.getFaixa(), filtros.getHorario(), filtros.getNaipe());
        return ResponseEntity.ok("");
    }

    @GetMapping("listaInscritos")
    public ResponseEntity listaInscritos() {
        List<Aluno> alunos = alunoRepository.findAll();
        return ResponseEntity.ok("");
    }

    @PostMapping("listaInscritos")
    public ResponseEntity listaInscritosFiltrada(FiltroAlunos dados){
        List<Aluno> alunos = alunoRepository.findByTodosFiltros(dados.getNome(), dados.getModalidade(), dados.getStatus(), dados.getEtnia(), dados.getSexo());
        return ResponseEntity.ok("");
    }

}
