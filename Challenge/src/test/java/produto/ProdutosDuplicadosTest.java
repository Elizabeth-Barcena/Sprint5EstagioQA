package produto;

import static constants.Endpoints.LOGIN;
import static constants.Endpoints.USUARIOS;
import static constants.Endpoints.PRODUTOS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

import dataFactory.DynamicFactory;
import helper.BaseTest;
import io.restassured.response.Response;
import model.Produtos;
import model.Usuario;

public class ProdutosDuplicadosTest extends BaseTest{
	private static Faker faker = new Faker(Locale.ENGLISH);
	private String idProdutos;
	private String idProdutos2;
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
			
	}
	@Test
	public void criarUsuarioComIdInexistenteComPut() {
		Date date = new Date();
		String idInexistente = date.getTime() + "inexistente";
		
		Produtos produto = DynamicFactory.generateRandomProduto(); 
        Response response = produtosServiceInherit.putProdutos(PRODUTOS +"/"+ idInexistente, produto, token);
        
        idProdutos2 = response.path( "_id");
		assertThat(response.statusCode(), is(201));
	}
	
	@Test
	public void CadastrarProdutoComNomeJaUtilizado() {
		Produtos produto = DynamicFactory.generateRandomProduto(); 
        
        Response response3 = produtosServiceInherit.postProdutos(PRODUTOS, produto, token);
		
		idProdutos = response3.path( "_id");
		
		Produtos produto1 = new Produtos();	
		
		produto1.setNome(produto.getNome());
		produto1.setPreco(faker.number().numberBetween(0, 10000));
        produto1.setDescricao(faker.lorem().sentence());
        produto1.setQuantidade(faker.number().randomDigit());
        
        Response response = produtosServiceInherit.postProdutos(PRODUTOS, produto, token);
			
		idProdutos2 = response.path("_id");
		
		assertThat(response.statusCode(), is(400));
		
	}
	@AfterEach
	public void removerProdutos(){
		if(idProdutos!= null) {
			response = rest.deleteProdutos(PRODUTOS +"/"+ idProdutos, token);
				}
		if(idProdutos2 != null) {
			response = rest.deleteProdutos(PRODUTOS +"/"+ idProdutos2, token);
		}
	
	 }
	@AfterEach
	public void removerUsuarios(){
		if(idUsuario != null) {
			response = usuariosServiceInherit.delete(USUARIOS +"/"+ idUsuario);
		}
	 
	}
}
