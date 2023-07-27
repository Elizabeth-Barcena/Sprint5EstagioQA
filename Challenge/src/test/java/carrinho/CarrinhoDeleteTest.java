package carrinho;

import static constants.Endpoints.CARRINHO;
import static constants.Endpoints.CARRINHOCONCLUIR;
import static constants.Endpoints.CARRINHOCANCELARCOMPRA;
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

import dataFactory.DynamicFactory;
import helper.BaseTest;
import io.restassured.response.Response;
import model.Carrinho;
import model.ProdutosCarrinho;
import model.Usuario;
import services.CarrinhoService;

public class CarrinhoDeleteTest extends BaseTest{
	private String idProdutos;
	private String token;
	private String idCarrinho;
	private String idUsuario;
	public Response response;
	
	@BeforeEach
	public void usuarioLogin() {
		Usuario usuario = DynamicFactory.generateRandomUsuario("true");
		response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path("_id");
		
		Map<String, String> usuarioLogin = new HashMap<>();
		usuarioLogin.put("email", usuario.getEmail());
		usuarioLogin.put("password", usuario.getPassword());
		Response response2 = loginServiceInherit.post(LOGIN, usuarioLogin);
		
		token = response2.path("authorization");
		produto = DynamicFactory.generateRandomProduto();
		Response response3 = rest.postProdutos(PRODUTOS, produto, token);
		idProdutos = response3.path("_id"); 
		
		Carrinho car = new CarrinhoService().car();
		car.addProduto((new ProdutosCarrinho(idProdutos,4)));
        Response responseCadastrarCarrinho = rest.postProdutos(CARRINHO, car, token);
        idCarrinho = responseCadastrarCarrinho.path("_id");
        System.out.println(idCarrinho);
        assertThat(responseCadastrarCarrinho.statusCode(), is(201));
	
	}
	@Test
	public void ConcluirUmaCompra() {
		response = rest.deleteProdutos(CARRINHOCONCLUIR, token);
		assertThat(response.statusCode(), is(200));
	}
	@Test
	public void ConcluirUmaCompraSemToken() {
		response = rest.deleteProdutos(CARRINHOCONCLUIR, "");
		assertThat(response.statusCode(), is(401));
	}
	@Test
	public void CancelarUmaCompra() {
		response = rest.deleteProdutos(CARRINHOCANCELARCOMPRA, token);
		assertThat(response.statusCode(), is(200));
	}
	@Test
	public void CancelarUmaCompraSemToken() {
		response = rest.deleteProdutos(CARRINHOCANCELARCOMPRA, "");
		assertThat(response.statusCode(), is(401));
	}
	@AfterEach
	public void deleteConcluirCompra() {
		response = rest.deleteProdutos(CARRINHOCANCELARCOMPRA, token);
		
		if(idProdutos!= null) {
			response = rest.deleteProdutos(PRODUTOS +"/"+ idProdutos, token);
				}
		if(idUsuario != null) {
			response = usuariosServiceInherit.delete(USUARIOS +"/"+ idUsuario);
		}
	}
}


