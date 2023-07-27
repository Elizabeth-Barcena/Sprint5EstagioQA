package produto;

import static constants.Endpoints.USUARIOS;
import static constants.Endpoints.PRODUTOS;
import static constants.Endpoints.LOGIN;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Epic("Endpoint/produtos utilizando o verbo POST")
public class ProdutosPostTest extends BaseTest{
	private String idProdutos;
	private String idUsuario;
	private String token;
	public Response response;
	
	@BeforeEach
	public void usuarioLogin (){
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
	public void CadastrarProdutos() {
		Response response = produtosServiceInherit.postProd(token);
		
		idProdutos = response.path( "_id");
		assertThat(response.statusCode(), is(201));
		
	}	
	@Test
	public void CadastrarProdutoSemNome() {
		Produtos produto = DynamicFactory.gerarProdutoSemNome();
        
        Response response = produtosServiceInherit.postProdutos(PRODUTOS, produto, token);
			
		idProdutos = response.path( "_id");
		assertThat(response.statusCode(), is(400));
	}
	
	
	@Test
	public void CadastrarProdutoComPrecoNulo() {
		Produtos produto = DynamicFactory.gerarProdutoComPrecoNull();
        Response response = produtosServiceInherit.postProdutos(PRODUTOS, produto, token);
		idProdutos = response.path( "_id");
		assertThat(response.statusCode(), is(400));
	}
	
	@Test
	public void CadastrarProdutoSemDescrição() {
		Produtos produto = DynamicFactory.gerarProdutoSemDescricao();
        Response response = produtosServiceInherit.postProdutos(PRODUTOS, produto, token);
		idProdutos = response.path( "_id");
		assertThat(response.statusCode(), is(400));
	}
	
	@Test
	public void CadastrarProdutoComQuantidadeNula() {
		Produtos produto = DynamicFactory.gerarProdutoComQuantidadeNula();
        Response response = produtosServiceInherit.postProdutos(PRODUTOS, produto, token);
			
		idProdutos = response.path( "_id");
		assertThat(response.statusCode(), is(400));
	}
	
	@Test
	public void CadastrarProdutoComTodosOsCamposNulos() {
		Produtos produto = DynamicFactory.gerarProdutoComTodosOsCamposNulos();
        Response response = produtosServiceInherit.postProdutos(PRODUTOS, produto, token);
			
		idProdutos = response.path( "_id");
		assertThat(response.statusCode(), is(400));
	}
	@Test
	public void CadastrarProdutoSemToken() {
		Produtos produto = DynamicFactory.generateRandomProduto();        
        Response response = produtosServiceInherit.postProdutos(PRODUTOS, produto,"");
			
		idProdutos = response.path( "_id");
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
