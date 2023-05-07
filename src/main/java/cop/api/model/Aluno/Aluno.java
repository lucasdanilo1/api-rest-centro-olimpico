package cop.api.model.Aluno;

import cop.api.model.Aluno.DTO.DadosCadastroAluno;
import cop.api.model.Aluno.enums.Status;
import cop.api.model.Turma.Turma;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import cop.api.service.VerificadorDeElegibilidadeDeAlunoEmTurma;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Entity(name = "Aluno")
@Table(name="alunos")
@EqualsAndHashCode
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    private String dataEnvio;
    @Embedded
    private DadosPessoais dadosPessoais;
    @ManyToOne
    private Turma turma;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Transient
    AlunoManager manager = new AlunoManager();
    @Transient
    VerificadorDeElegibilidadeDeAlunoEmTurma verificador  = new VerificadorDeElegibilidadeDeAlunoEmTurma();

    public Aluno(DadosCadastroAluno dados){
        setAtivo(true);
        setStatus(Status.INSCRITO);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        this.dataEnvio = formatter.format(data);
        this.dadosPessoais = new DadosPessoais(dados);
    }

    public void setTurma(Turma turma) {
        verificador.verificarElegibilidade(turma, this);
        this.turma = turma;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    }