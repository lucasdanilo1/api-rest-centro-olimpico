package cop.api.Controller;

import cop.api.Model.Usuario.DTO.DadosValidacao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sigecop")
public class AutenticacaController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosValidacao dados){
        var token = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }


}
