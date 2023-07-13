package cop.api.Service;

import cop.api.exceptions.StatusAlunoException;
import cop.api.exceptions.ValidacaoAlunoParaTurmaException;
import cop.api.model.Aluno.Aluno;
import cop.api.model.Aluno.DTO.DadosAtualizaAluno;
import cop.api.model.Aluno.DTO.DadosCadastroAluno;
import cop.api.model.Aluno.DTO.DadosPessoaisDTO;
import cop.api.model.Aluno.DTO.DadosPessoaisParaAtualizacao;
import cop.api.model.Aluno.enums.*;
import cop.api.model.Turma.DTO.DadosCadastroTurma;
import cop.api.model.Turma.Turma;
import cop.api.model.Turma.enums.*;
import cop.api.service.AlunoService;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Transactional
class AlunoServiceTest {

    @Autowired
    AlunoService service;

    @Test
    @DisplayName("Deve ativar aluno inativado")
    void ativa_1() throws Exception {
        var aluno = cadastrarAluno(2);
        service.ativa(aluno);
        Assert.assertTrue(aluno.getAtivo());
    }

    @Test
    @DisplayName("Deve inativar aluno ativado")
    void inativa_1() throws Exception {
        var aluno = cadastrarAluno(1);
        service.inativa(aluno);
        Assert.assertFalse(aluno.getAtivo());
    }

    @Test
    @DisplayName("Deve selecionar aluno inscrito")
    void seleciona_1() {
        var aluno = cadastrarAluno(1);
        aluno.setTurma(criaTurma(1));
        service.seleciona(aluno);
        Assert.assertEquals(aluno.getStatus(), Status.SELECIONADO);
    }

    @Test
    @DisplayName("Deve matricular aluno selecionado")
    void matricula_1() {
        var aluno = cadastrarAluno(1);
        aluno.setTurma(criaTurma(1));
        service.seleciona(aluno);
        service.matricula(aluno);
        Assert.assertEquals(aluno.getStatus(), Status.MATRICULADO);
    }

    @Test
    @DisplayName("Deve mudar turma de aluno")
    void mudaTurma_1(){
        var aluno = cadastrarAluno(1);
        Turma turma1 = criaTurma(1);
        service.setTurma(turma1, aluno);
        Assert.assertEquals(aluno.getTurma(), turma1);
    }

    @Test
    @DisplayName("Deve atualizar informações de aluno")
    void atualiza_1() {
        var aluno = cadastrarAluno(1);
        DadosAtualizaAluno dados = dadosParaAtualizacao();
        service.atualizaInformacoes(aluno, dados);
        Assert.assertEquals(aluno.getDadosPessoais().getNome(), "Danilo");
    }

    @Test
    @DisplayName("Deve devolver exception ao ativar aluno ja ativado")
    void ativa_2() throws Exception {
        var aluno = cadastrarAluno(1);
        Assert.assertThrows(StatusAlunoException.class, () -> service.ativa(aluno));
    }

    @Test
    @DisplayName("Deve devolver exception ao tentar selecionar aluno diferente de inscrito")
    void seleciona_2() {
        var aluno = cadastrarAluno(1);
        aluno.setTurma(criaTurma(1));
        service.inativa(aluno);
        Assert.assertThrows(StatusAlunoException.class, () -> service.seleciona(aluno));
    }

    @Test
    @DisplayName("Deve devolver exception ao tentar matricular aluno diferente de selecionado")
    void matricula_2() {
        var aluno = cadastrarAluno(1);
        aluno.setTurma(criaTurma(1));
        service.inativa(aluno);
        Assert.assertThrows(StatusAlunoException.class, () -> service.matricula(aluno));
    }

    @Test
    @DisplayName("Deve devolver exception caso aluno esteja fora de alguma restrição da turma")
    void mudaTurma_2(){
        var aluno = cadastrarAluno(1);
        Turma turma = criaTurma(2);
        Turma turmaFaixaDiferente = criaTurma(2);
        Turma turmaNaipeDiferente = criaTurma(3);
        Assert.assertEquals(turma.getStatus(), StatusTurma.TURMA_FECHADA);
        Assert.assertThrows(ValidacaoAlunoParaTurmaException.class, () -> service.setTurma(turmaFaixaDiferente, aluno));
        Assert.assertThrows(ValidacaoAlunoParaTurmaException.class, () -> service.setTurma(turmaNaipeDiferente, aluno));
    }

    private Turma criaTurma(int opcao){
        DadosCadastroTurma dados = new DadosCadastroTurma(Dias.QUARTA_SEXTA, Horario.HORA_19_20, Faixa.FAIXA_18MAIS, Naipe.MASCULINO, Modalidade.VOLEI, 1);
        DadosCadastroTurma dadosTurma3 = new DadosCadastroTurma(Dias.QUARTA_SEXTA, Horario.HORA_19_20, Faixa.FAIXA_9_12, Naipe.MASCULINO, Modalidade.VOLEI, 1);
        DadosCadastroTurma dadosTurma4 = new DadosCadastroTurma(Dias.QUARTA_SEXTA, Horario.HORA_19_20, Faixa.FAIXA_9_12, Naipe.FEMININO, Modalidade.VOLEI, 1);

        var turma = new Turma(dados);

        if(opcao == 1){
          return turma;
        }else if(opcao == 2){
            turma.setStatus(StatusTurma.TURMA_FECHADA);
            return turma;
        }else if(opcao == 3){
            return new Turma(dadosTurma3);
        }
        else if(opcao == 4){
            return new Turma(dadosTurma4);
        }
        return turma;
    }

    private Aluno cadastrarAluno(int opcao) {
        var dados = new DadosPessoaisDTO("Lucas", Sexo.MASCULINO, 2004, "51914384504", Naturalidade.ACREANO, Etnia.PARDO, "Aufhsdfu", "ishjdsoua@gmail.com", "6140028922", Regiao.GAMA, "shudiaudhsuo");

        if(opcao == 1){
            var dadosCadastro = new DadosCadastroAluno(5L, dados);
            var aluno = new Aluno(dadosCadastro);
            var turma = criaTurma(1);
            aluno.setTurma(turma);
            return aluno;
        }else if(opcao == 2){
            var dadosCadastro = new DadosCadastroAluno(5L, dados);
            var aluno = new Aluno(dadosCadastro);
            service.inativa(aluno);
            return aluno;
        }
        return null;
    }

    private DadosAtualizaAluno dadosParaAtualizacao(){

        DadosPessoaisParaAtualizacao dados = new DadosPessoaisParaAtualizacao();
        dados.setNome("Danilo");
        DadosAtualizaAluno dadosAtualizaAluno = new DadosAtualizaAluno();
        dadosAtualizaAluno.setDadosPessoais(dados);
        return dadosAtualizaAluno;
    }
}

