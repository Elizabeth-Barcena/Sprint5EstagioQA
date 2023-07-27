package produto;

import static org.hamcrest.Matchers.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Usuario;
import services.BaseRest;
import dataFactory.DynamicFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import helper.BaseTest;
import io.restassured.response.Response;
import static constants.Endpoints.USUARIOS;
import static constants.Endpoints.LOGIN;
import static constants.Endpoints.PRODUTOS;

public class ProdutosGetTest extends BaseTest{
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
	public void deveRetornarListaDeProdutos() {
		BaseRest rest = new BaseRest();
		Response response = rest.get(PRODUTOS);
		assertThat(response.statusCode(), is(200));

	}

	@Test
	public void deveRetornarProdutoPorId() {
		//System.out.println(idUsuario);
		Response response = produtosServiceInherit.getProdutos("/"+ idProdutos);
		assertThat(response.statusCode(), is(200));
	}
	@Test
	public void ListaProdutoNãoExistente() {
		Date date = new Date();
		String idNaoExistente = date.getTime()+ "naoexiste";
		Response response = produtosServiceInherit.getProdutos("/"+ idNaoExistente);
		assertThat(response.path("message"), equalTo("Produto não encontrado"));
		assertThat(response.statusCode(), is(400));
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
