package cop.api.Model.Aluno.DTO;

import cop.api.Model.Aluno.Etnia;
import cop.api.Model.Aluno.Naturalidade;
import cop.api.Model.Aluno.Regiao;
import cop.api.Model.Aluno.Sexo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosPessoaisParaAtualizacao {

    private String nome;
    private Sexo sexo;
    private Integer anoNascimento;
    private String cpf;
    @NotNull
    private Naturalidade naturalidade;
    @NotNull
    private Etnia etnia;
    private String nomeDaMae;
    private String email;
    private String telefone;
    @NotNull
    private Regiao regiao;
    private String endereco;

}
