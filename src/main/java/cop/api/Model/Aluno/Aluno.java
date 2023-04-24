package cop.api.Model.Aluno;

import cop.api.Model.Aluno.DTO.DadosAtualizaAluno;
import cop.api.Model.Aluno.DTO.DadosCadastroAluno;
import cop.api.Model.Turma.Turma;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@Table(name="alunos")
@EqualsAndHashCode
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    Boolean ativo;
    private String dataEnvio;
    @Embedded
    private DadosPessoais dadosPessoais;
    @Transient
    private VerificadorDeAlunoParaTurma verificaAluno = new VerificadorDeAlunoParaTurma();
    @Transient
    public AlunoManager manager = new AlunoManager();;
    @ManyToOne
    Turma turma;
    @Enumerated(EnumType.STRING)
    private Status statusAluno;

    public Aluno(DadosCadastroAluno dados){
        this.ativo = true;
        this.statusAluno = Status.INSCRITO;
        this.dadosPessoais = new DadosPessoais(dados);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        this.dataEnvio = formatter.format(data);
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setTurma(Turma turma){
        verificaAluno.verificarElegibilidade(turma,this);
        turma.getInscritos().add(this);
        this.turma = turma;
    }

    public void inativaAluno(){
        manager.inativaAluno(this);
    }

    public void ativaAluno(){
        manager.ativaAluno(this);
    }

    public void selecionaAluno(){
        manager.selecionaAluno(this);
    }

    public void matriculaAluno() {
        manager.matriculaAluno(this);
    }

    public void alteraTurma(Turma turma){
        manager.alteraTurma(turma, this);
    }

    public void setStatusAluno(Status statusAluno) {
        this.statusAluno = statusAluno;
    }

    public Turma turmaAtual() {
        return turma;
    }

    public void atualizaInformacoes(DadosAtualizaAluno dados) {
        if(dados.getDadosPessoais() != null){
            this.dadosPessoais.checaCamposDadosPessoaisAtualiza(dados);
        }
    }
}