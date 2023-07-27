package usuario;

import static constants.Endpoints.USUARIOS;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Usuario;
import dataFactory.DynamicFactory;
import helper.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;


@Epic("Endpoint/usuarios utilizando o verbo GET")

public class UsuariosGetTest extends BaseTest {
	public Response response;
	public String idUsuario;
	public String nomeUsuario;
	
	
	//Roda uma vez antes de cada teste
	@BeforeEach
	public void adicionaUsuario() {
		Usuario usuario = DynamicFactory.generateRandomUsuario("true");
		
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		
		System.out.println(nomeUsuario);
		assertThat(response.statusCode(), is(201));
		assertThat(response.path("message"), equalTo("Cadastro realizado com sucesso"));

	}
	@Description("Deve retornar lista de usuarios")
	@Test
	public void deveRetornarListaDeUsuarios() {
		
			Response response= usuariosServiceInherit.getUsuarios();
			assertThat(response.statusCode(), is(200));
			
		}

	@Description("Deve retornar um usuário especifico")
	@Test
	public void deveRetornarUsuarioComId() {
		
		Response response = usuariosServiceInherit.getUsuario("/"+ idUsuario);
		assertThat(response.statusCode(), is(200));

	}
	
	@Description("Não deve retornar um usuário")
	@Test
	public void naoDeveRetornarUsuarioComIdInexistente() {
		Date date = new Date();
		String usuarioInexistente = date.getTime() + "inexistente";
		
		Response response = usuariosServiceInherit.getUsuario("/"+ usuarioInexistente);
		assertThat(response.path("message"), equalTo("Usuário não encontrado"));
		assertThat(response.statusCode(), is(400));

	}
	
	@AfterEach
	public void removerUsuarioDasValidacoes(){
		if(idUsuario != null) {
			response = rest.delete(USUARIOS +"/"+ idUsuario);
				}
	 }
	
}
