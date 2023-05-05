package cop.api.model.Aluno.DTO;

import cop.api.model.Aluno.enums.Etnia;
import cop.api.model.Aluno.enums.Naturalidade;
import cop.api.model.Aluno.enums.Regiao;
import cop.api.model.Aluno.enums.Sexo;
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
