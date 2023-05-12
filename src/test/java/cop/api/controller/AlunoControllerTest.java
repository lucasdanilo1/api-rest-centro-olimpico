package cop.api.controller;

import cop.api.model.Aluno.Aluno;
import cop.api.model.Aluno.DTO.DadosCadastroAluno;
import cop.api.model.Aluno.DTO.DadosPessoaisDTO;
import cop.api.model.Aluno.enums.Etnia;
import cop.api.model.Aluno.enums.Naturalidade;
import cop.api.model.Aluno.enums.Regiao;
import cop.api.model.Aluno.enums.Sexo;
import cop.api.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class AlunoControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    AlunoRepository repository;

    @Test
    @DisplayName("Se o aluno estiver ativado, deve me devolver codigo http 204")
    @WithMockUser
    void inativa_1() throws Exception {

        var aluno = cadastrarAluno();
        repository.save(aluno);

            var response = mvc.perform(delete("/sistema/inscritos/inativar/1"))
                    .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("Se o aluno estiver inativado, deve me devolver codigo http 204")
    @WithMockUser
    void ativa_1() throws Exception {

        var aluno = cadastrarAluno();
        repository.save(aluno);

        var response = mvc.perform(put("/sistema/inscritos/ativar/2"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void atualizar() {
    }

    @Test
    void selecionar() {
    }


    private Aluno cadastrarAluno(){
        var dados = new DadosPessoaisDTO("Lucas", Sexo.MASCULINO, 2004,"51914384504", Naturalidade.ACREANO, Etnia.PARDO, "Aufhsdfu", "ishjdsoua@gmail.com","6140028922", Regiao.GAMA, "shudiaudhsuo");
        var dadosCadastro = new DadosCadastroAluno(5L, dados);
        var aluno = new Aluno(dadosCadastro);
        repository.save(aluno);
        return aluno;
    }
//
//    private Turma cadastrarTurma(){
//        var dadosCadastro = new DadosCadastroTurma(Dias.QUARTA_SEXTA, Horario.HORA_18_19, Faixa.FAIXA_18MAIS, Naipe.MASCULINO, Modalidade.ATLETISMO, 20);
//        var turma = new Turma(dadosCadastro);
//        em.persist(turma);
//        return turma;
//    }

}