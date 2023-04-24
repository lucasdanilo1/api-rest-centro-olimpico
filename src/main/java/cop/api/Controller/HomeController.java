package cop.api.Controller;

import cop.api.Model.Aluno.Aluno;
import cop.api.Model.Aluno.DTO.DadosCadastroAluno;
import cop.api.Model.Aluno.Repository.AlunoRepository;
import cop.api.Model.Exceptions.AlunoJaCadastradoException;
import cop.api.Model.Turma.Repository.TurmaRepository;
import cop.api.Model.Turma.Turma;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sigecop")
public class HomeController {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private TurmaRepository turmaRepository;


    @Transactional
    @PostMapping("cadastro")
    public ResponseEntity cadastrarAluno(@RequestBody @Valid DadosCadastroAluno dados) throws Exception {
        if(alunoRepository.existsByDadosPessoaisCpf(dados.getDadosPessoais().getCpf())){
            throw new AlunoJaCadastradoException();
        }else {
            Turma turma = turmaRepository.findById(dados.getTurmaId()).orElseThrow(() -> new Exception("Turma não encontrada"));
            Aluno aluno = new Aluno(dados);
            aluno.setTurma(turma);
            alunoRepository.save(aluno);
        }
        return ResponseEntity.ok("");
    }

}
