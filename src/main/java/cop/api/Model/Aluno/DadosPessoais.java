package cop.api.Model.Aluno;

import cop.api.Model.Aluno.DTO.DadosAtualizaAluno;
import cop.api.Model.Aluno.DTO.DadosCadastroAluno;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class DadosPessoais {

    private String nome;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private Integer anoNascimento;
    protected int idade;
    @Column(unique = true)
    private String cpf;
    @Enumerated(EnumType.STRING)
    private Naturalidade naturalidade;
    @Enumerated(EnumType.STRING)
    private Etnia etnia;
    private String nomeMae;
    private String email;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Regiao regiao;
    private String endereco;

    public DadosPessoais(DadosCadastroAluno dados){
        this.nome = dados.getDadosPessoais().getNome();
        this.sexo = dados.getDadosPessoais().getSexo();
        this.anoNascimento = dados.getDadosPessoais().getAnoNascimento();
        this.cpf = dados.getDadosPessoais().getCpf();
        this.naturalidade = dados.getDadosPessoais().getNaturalidade();
        this.etnia = dados.getDadosPessoais().getEtnia();
        this.nomeMae = dados.getDadosPessoais().getNomeDaMae();
        this.email = dados.getDadosPessoais().getEmail();
        this.telefone = dados.getDadosPessoais().getTelefone();
        this.regiao = dados.getDadosPessoais().getRegiao();
        this.endereco = dados.getDadosPessoais().getEndereco();
        this.idade = 2023 - dados.getDadosPessoais().getAnoNascimento();
    }

    public void checaCamposDadosPessoaisAtualiza(DadosAtualizaAluno dados){
        if(dados.getDadosPessoais().getNome() != null){
            this.nome = dados.getDadosPessoais().getNome();
        }
        if(dados.getDadosPessoais().getEndereco() != null){
            this.endereco = dados.getDadosPessoais().getEndereco();
        }
        if(dados.getDadosPessoais().getEtnia() != null){
            this.etnia = dados.getDadosPessoais().getEtnia();
        }
        if(dados.getDadosPessoais().getCpf() != null){
            this.cpf = dados.getDadosPessoais().getCpf();
        }
        if(dados.getDadosPessoais().getNaturalidade() != null){
            this.naturalidade = dados.getDadosPessoais().getNaturalidade();
        }
        if(dados.getDadosPessoais().getSexo() != null){
            this.sexo = dados.getDadosPessoais().getSexo();
        }
        if(dados.getDadosPessoais().getRegiao() != null){
            this.regiao = dados.getDadosPessoais().getRegiao();
        }
        if(dados.getDadosPessoais().getNomeDaMae() != null){
            this.nomeMae = dados.getDadosPessoais().getNomeDaMae();
        }
        if(dados.getDadosPessoais().getTelefone() != null){
            this.telefone = dados.getDadosPessoais().getTelefone();
        }
        if(dados.getDadosPessoais().getEmail() != null){
            this.email = dados.getDadosPessoais().getEmail();
        }
        if(dados.getDadosPessoais().getAnoNascimento() != null){
            this.anoNascimento = dados.getDadosPessoais().getAnoNascimento();
            this.idade = 2023 - dados.getDadosPessoais().getAnoNascimento();
        }
    }


}
