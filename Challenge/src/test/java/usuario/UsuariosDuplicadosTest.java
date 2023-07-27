package usuario;

import static constants.Endpoints.USUARIOS;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import model.Usuario;
import java.util.Date;
import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import com.github.javafaker.Faker;

import dataFactory.DynamicFactory;
import helper.BaseTest;
import io.qameta.allure.Description;

public class UsuariosDuplicadosTest extends BaseTest{
	public String idUser;
	public String idUser2;
	private static Faker faker = new Faker(Locale.ENGLISH);
	public Response response;

	@Test
	@Description("Cria um usuário já existente através do put")
	public void CriarUsuárioAtravésdoPutComEmailExistente() {
		Date date = new Date();
		String id = date.getTime() +  "usuario";
		
		Usuario usuario = DynamicFactory.generateRandomUsuario("true");
		
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUser = response.path( "_id");
		assertThat(response.statusCode(), is(201));
		assertThat(response.path("message"), equalTo("Cadastro realizado com sucesso"));
		
		Usuario usuario2 = new Usuario();
		
		usuario2.setNome(faker.name().fullName());
		usuario2.setEmail(usuario.getEmail());
		usuario2.setPassword(faker.internet().password());
		usuario2.setAdministrador("true");
		
		Response response2 = usuariosServiceInherit.put(USUARIOS+"/"+ id, usuario2);
		idUser2 = response2.path( "_id");
		assertThat(response2.statusCode(), is(400));
		
		
		
	} 
		@Test
		@Description("Cria um usuário com email já existente com post")
		public void CriarUsuárioComEmailJaExistente() {
		
		Usuario usuario = DynamicFactory.generateRandomUsuario("true");
		
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUser = response.path( "_id");
		assertThat(response.statusCode(), is(201));
		assertThat(response.path("message"), equalTo("Cadastro realizado com sucesso"));
		
		Usuario usuario2 = new Usuario();
		
		usuario2.setNome(faker.name().fullName());
		usuario2.setEmail(usuario.getEmail());
		usuario2.setPassword(faker.internet().password());
		usuario2.setAdministrador("true");

		Response response2 = usuariosServiceInherit.post(USUARIOS, usuario2);
		idUser2 = response2.path( "_id");
		assertThat(response2.statusCode(), is(400));

	}
	
	//Estou rodando varias vezes depois de todos os testes
		@AfterEach
		public void removerUsuarios(){
			if(idUser != null) {
			response = rest.delete(USUARIOS +"/"+ idUser);
				}
			
			if(idUser2 != null) {
				response = rest.delete(USUARIOS +"/"+ idUser2);
			}
		 }
}
