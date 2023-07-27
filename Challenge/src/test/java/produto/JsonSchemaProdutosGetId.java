package produto;

import static constants.Endpoints.LOGIN;
import static constants.Endpoints.PRODUTOS;
import static constants.Endpoints.USUARIOS;
import static helper.ServiceHelper.matchesJsonSchema;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Usuario;
import dataFactory.DynamicFactory;
import helper.BaseTest;

public class JsonSchemaProdutosGetId extends BaseTest {
	private String idProdutos;
	private String idUsuario;
	private String token;
	Response response;
	//Roda apenas uma vez antes de todos os testes
	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;	
		RestAssured.basePath = "";
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setContentType(ContentType.JSON);
		RestAssured.requestSpecification = reqBuilder.build();
		
	}
	@Test
	public void deveValidarSchemaNoRetornoDeUmProdutoComId() {
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
				 
		Response response1 = when().get("http://localhost:3000/produtos/"+idProdutos); 
		assertThat(response1.asString(), matchesJsonSchema("produtos", "get_id", 200));
		
	}
	@AfterEach
	public void removerUsuarioDasValidacoes(){
		if(idProdutos!= null) {
			response = rest.deleteProdutos(PRODUTOS +"/"+ idProdutos, token);
				}
		if(idUsuario != null) {
			response = usuariosServiceInherit.delete(USUARIOS +"/"+ idUsuario);
		}
	 }
}
