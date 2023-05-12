package cop.api.model.Aluno.DTO;

import cop.api.model.Aluno.enums.Etnia;
import cop.api.model.Aluno.enums.Naturalidade;
import cop.api.model.Aluno.enums.Regiao;
import cop.api.model.Aluno.enums.Sexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
public class DadosPessoaisDTO {

    @NotBlank
    private String nome;
    @NotNull
    private Sexo sexo;
    @NotNull
    private Integer anoNascimento;
    @NotBlank
    @CPF(message = "cpf inválido")
    private String cpf;
    @NotNull
    private Naturalidade naturalidade;
    @NotNull
    private Etnia etnia;
    @NotBlank
    private String nomeDaMae;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String telefone;
    @NotNull
    private Regiao regiao;
    @NotBlank
    private String endereco;

}
