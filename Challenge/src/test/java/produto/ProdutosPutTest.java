package produto;

import static constants.Endpoints.LOGIN;
import static constants.Endpoints.USUARIOS;
import static constants.Endpoints.PRODUTOS;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Produtos;
import model.Usuario;
import dataFactory.DynamicFactory;
import helper.BaseTest;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
@Epic("Endpoint/produtos utilizando o verbo POST")
public class ProdutosPutTest extends BaseTest {
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
	public void editarDadosDoProduto() {
		Produtos produto = DynamicFactory.generateRandomProduto();  
        
        Response response = produtosServiceInherit.putProdutos(PRODUTOS +"/"+ idProdutos, produto, token);
		assertThat(response.statusCode(), is(200));
		assertThat(response.path("message"), equalTo("Registro alterado com sucesso"));
	}
	@Test
	public void editarProdutosSemToken() {
		Produtos produto = DynamicFactory.generateRandomProduto();  
       
        Response response = produtosServiceInherit.putProdutos(PRODUTOS +"/"+ idProdutos, produto, "");
		assertThat(response.statusCode(), is(401));
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
