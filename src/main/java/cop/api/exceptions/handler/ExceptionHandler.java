package cop.api.exceptions.handler;

import cop.api.exceptions.AlunoJaCadastradoException;
import cop.api.exceptions.StatusAlunoException;
import cop.api.exceptions.ValidacaoAlunoParaTurmaException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

        @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity tratarErro404(){
            return ResponseEntity.notFound().build();
        }

        @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
            var erros = ex.getFieldErrors();
            return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
        }

        @org.springframework.web.bind.annotation.ExceptionHandler(AlunoJaCadastradoException.class)
        public ResponseEntity tratarErroCpfJaCadastrado(AlunoJaCadastradoException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        @org.springframework.web.bind.annotation.ExceptionHandler(StatusAlunoException.class)
        public ResponseEntity tratarErroStatusAluno(StatusAlunoException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        @org.springframework.web.bind.annotation.ExceptionHandler(ValidacaoAlunoParaTurmaException.class)
        public ResponseEntity tratarErroCpfJaCadastrado(ValidacaoAlunoParaTurmaException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }



        private record DadosErroValidacao(String campo, String mensagem){
            public DadosErroValidacao(FieldError erro){
                this(erro.getField(), erro.getDefaultMessage());
            }
        }

}

