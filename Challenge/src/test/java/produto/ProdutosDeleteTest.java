package produto;

import static constants.Endpoints.LOGIN;
import static constants.Endpoints.PRODUTOS;
import static constants.Endpoints.USUARIOS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Usuario;
import dataFactory.DynamicFactory;
import helper.BaseTest;
import io.qameta.allure.Epic;

import io.restassured.response.Response;

@Epic("Endpoint/produtos utilizando o verbo DELETE")
public class ProdutosDeleteTest extends BaseTest{
	private String idProdutos;
	private String idUsuario;
	private String token;
	public Response response;
	@BeforeEach
	public void usuarioLogin() {
		Usuario usuario = DynamicFactory.generateRandomUsuario("true");
		
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		
		Map<String, String> usuarioLogin = new HashMap<>();
		usuarioLogin.put("email", usuario.getEmail());
		usuarioLogin.put("password", usuario.getPassword());
		
		Response response2 = loginServiceInherit.post(LOGIN, usuarioLogin);
		token = response2.path("authorization");
		
		Response response3 = produtosServiceInherit.postProd(token);
				
		idProdutos = response3.path( "_id");
	}
	@Test
	public void excluirProdutoSemToken() {
		Response response = rest.deleteProdutos(PRODUTOS +"/"+ idProdutos, "");
		assertThat(response.statusCode(), is(401));
	}

	
public void ExcluirProdutoComUsuarioQueNãoéAdm() {
		Usuario usuario = DynamicFactory.generateRandomUsuario("false");
		
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		
		Map<String, String> usuarioLogin = new HashMap<>();
		usuarioLogin.put("email", usuario.getEmail());
		usuarioLogin.put("password", usuario.getPassword());
		
		Response response2 = loginServiceInherit.post(LOGIN, usuarioLogin);
		token = response2.path("authorization");
		
		Response response3 = produtosServiceInherit.postProd(token);
				
		idProdutos = response3.path( "_id");
	}
	@AfterEach
	public void removerProdutos(){
		if(idProdutos!= null) {
			response = rest.deleteProdutos(PRODUTOS +"/"+ idProdutos, token);
				}
		if(idUsuario != null) {
			response = usuariosServiceInherit.delete(USUARIOS +"/"+ idUsuario);
		}
	}
}
