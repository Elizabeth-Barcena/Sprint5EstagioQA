package services;

import model.Usuario;
import static constants.Endpoints.USUARIOS;
import java.util.Locale;
import com.github.javafaker.Faker;
import io.restassured.response.Response;


public class UsuariosServiceInherit extends BaseRest{
	private static Faker faker = new Faker(Locale.ENGLISH);
	public Response getUsuario(String id) {
		return get(USUARIOS + id);
	}
	
	public Response post(String isAdmin) {
		Usuario usuario = new Usuario();
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setPassword(faker.internet().password());
		usuario.setAdministrador(isAdmin);
		return post(USUARIOS, usuario);
		
	}
	public Response getUsuarios(){
		return get(USUARIOS);
	}

}
