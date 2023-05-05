package cop.api.repository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositoryImpl extends UsuarioRepository{

    UserDetails findByLogin(String login);

}
