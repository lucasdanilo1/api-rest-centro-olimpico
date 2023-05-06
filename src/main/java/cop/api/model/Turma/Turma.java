package cop.api.model.Turma;

import cop.api.model.Aluno.Aluno;
import cop.api.model.Turma.DTO.DadosCadastroTurma;
import cop.api.model.Turma.enums.StatusTurma;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity(name = "Turma")
@Table(name = "Turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Embedded
    private DadosTurma dadosTurma;
    private StatusTurma status;
    @OneToMany(mappedBy = "turma")
    private List<Aluno> inscritos = new ArrayList<>();
    @Transient
    private List<Aluno> matriculados = new ArrayList<>();
    static private int numeroTurma = 1;

    public Turma(DadosCadastroTurma dados){
        this.nome = String.format("T-%03d", numeroTurma++);
        this.status = StatusTurma.TURMA_ABERTA;
        this.dadosTurma = new DadosTurma(dados);
    }
}

