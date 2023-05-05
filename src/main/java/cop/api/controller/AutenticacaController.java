package cop.api.controller;

import cop.api.security.TokenService;
import cop.api.model.Usuario.DTO.DadosValidacao;
import cop.api.model.Usuario.Usuario;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosValidacao dados){

        //spring
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());
        var authentication = manager.authenticate(authenticationToken);

        var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(token);
    }


}
