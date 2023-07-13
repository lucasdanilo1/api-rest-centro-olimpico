package cop.api.Service;

import cop.api.exceptions.AlunoJaCadastradoException;
import cop.api.model.Aluno.Aluno;
import cop.api.model.Aluno.DTO.DadosCadastroAluno;
import cop.api.model.Aluno.DTO.DadosPessoaisDTO;
import cop.api.model.Aluno.enums.Etnia;
import cop.api.model.Aluno.enums.Naturalidade;
import cop.api.model.Aluno.enums.Regiao;
import cop.api.model.Aluno.enums.Sexo;
import cop.api.model.Turma.DTO.DadosCadastroTurma;
import cop.api.model.Turma.Turma;
import cop.api.model.Turma.enums.*;
import cop.api.repository.AlunoRepository;
import cop.api.repository.TurmaRepository;
import cop.api.service.AlunoService;
import cop.api.service.CadastroAlunoService;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class CadastroAlunoServiceTest {

    @Autowired
    private CadastroAlunoService cadastro;

    @Autowired
    private TurmaRepository turma;

    @Autowired
    private AlunoService service;

    @Test
    @DisplayName("Deve cadastrar um aluno corretamente e checar todos os campos de dados")
    void cadastro() {
        AlunoRepository alunoRepositoryMock = Mockito.mock(AlunoRepository.class);
        TurmaRepository turmaRepositoryMock = Mockito.mock(TurmaRepository.class);
        AlunoService serviceMock = Mockito.mock(AlunoService.class);

        cadastro = new CadastroAlunoService(alunoRepositoryMock, turmaRepositoryMock, serviceMock);

        var dadosPessoais = new DadosPessoaisDTO("Lucas", Sexo.MASCULINO, 2004, "51914384504", Naturalidade.ACREANO, Etnia.PARDO, "Aufhsdfu", "ishjdsoua@gmail.com", "6140028922", Regiao.GAMA, "shudiaudhsuo");

        DadosCadastroAluno dados = new DadosCadastroAluno(1L, dadosPessoais);

        Turma turma = criaTurma(1);

        Mockito.when(turmaRepositoryMock.existsById(1L)).thenReturn(true);
        Mockito.when(turmaRepositoryMock.findById(any())).thenReturn(Optional.of(turma));

        cadastro.cadastro(dados);

        ArgumentCaptor<Aluno> alunoCaptor = ArgumentCaptor.forClass(Aluno.class);

        Mockito.verify(alunoRepositoryMock).save(alunoCaptor.capture());

        Aluno alunoCapturado = alunoCaptor.getValue();

        Assert.assertEquals("Lucas", alunoCapturado.getDadosPessoais().getNome());
        Assert.assertEquals(Sexo.MASCULINO, alunoCapturado.getDadosPessoais().getSexo());
        Assert.assertEquals("51914384504", alunoCapturado.getDadosPessoais().getCpf());
        Assert.assertEquals(Naturalidade.ACREANO, alunoCapturado.getDadosPessoais().getNaturalidade());
        Assert.assertEquals(Etnia.PARDO, alunoCapturado.getDadosPessoais().getEtnia());
        Assert.assertEquals("shudiaudhsuo", alunoCapturado.getDadosPessoais().getEndereco());
        Assert.assertEquals("ishjdsoua@gmail.com", alunoCapturado.getDadosPessoais().getEmail());
        Assert.assertEquals("6140028922", alunoCapturado.getDadosPessoais().getTelefone());
        Assert.assertEquals(Regiao.GAMA, alunoCapturado.getDadosPessoais().getRegiao());
        Assert.assertEquals("shudiaudhsuo", alunoCapturado.getDadosPessoais().getEndereco());
    }

    @Test
    @DisplayName("Deve retornar exceção ao tentar cadastrar aluno em uma turma com id inexistente")
    void cadastro_1(){
        AlunoRepository alunoRepositoryMock = Mockito.mock(AlunoRepository.class);
        TurmaRepository turmaRepositoryMock = Mockito.mock(TurmaRepository.class);
        AlunoService serviceMock = Mockito.mock(AlunoService.class);

        var cadastro = new CadastroAlunoService(alunoRepositoryMock, turmaRepositoryMock, serviceMock);

        var dadosPessoais = new DadosPessoaisDTO("Lucas", Sexo.MASCULINO, 2004, "51914384504", Naturalidade.ACREANO, Etnia.PARDO, "Aufhsdfu", "ishjdsoua@gmail.com", "6140028922", Regiao.GAMA, "shudiaudhsuo");
        DadosCadastroAluno dados = new DadosCadastroAluno(1L, dadosPessoais);

        Mockito.when(turmaRepositoryMock.existsById(any())).thenReturn(false);

        Assert.assertThrows(RuntimeException.class, () -> cadastro.cadastro(dados));
    }

    @Test
    @DisplayName("Deve retornar exceção ao tentar cadastrar aluno com cpf já cadastrado no banco")
    void cadastro_2(){

        AlunoRepository alunoRepositoryMock = Mockito.mock(AlunoRepository.class);
        TurmaRepository turmaRepositoryMock = Mockito.mock(TurmaRepository.class);
        AlunoService serviceMock = Mockito.mock(AlunoService.class);

        var cadastro = new CadastroAlunoService(alunoRepositoryMock, turmaRepositoryMock, serviceMock);

        var dadosPessoais = new DadosPessoaisDTO("Lucas", Sexo.MASCULINO, 2004, "51914384504", Naturalidade.ACREANO, Etnia.PARDO, "Aufhsdfu", "ishjdsoua@gmail.com", "6140028922", Regiao.GAMA, "shudiaudhsuo");
        DadosCadastroAluno dados = new DadosCadastroAluno(1L, dadosPessoais);

        Mockito.when(turmaRepositoryMock.existsById(any())).thenReturn(true);
        Mockito.when(alunoRepositoryMock.existsByDadosPessoaisCpf(any())).thenReturn(true);

        Assert.assertThrows(AlunoJaCadastradoException.class, () -> cadastro.cadastro(dados));

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

}
