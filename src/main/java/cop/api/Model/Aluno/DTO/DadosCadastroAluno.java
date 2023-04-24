package cop.api.Model.Aluno.DTO;

import cop.api.Model.Aluno.DTO.DadosPessoaisDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosCadastroAluno{

        @NotNull
        private Long turmaId;
        @NotNull @Valid
        private DadosPessoaisDTO dadosPessoais;

}
