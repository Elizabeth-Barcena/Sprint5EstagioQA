package usuario;

import static constants.Endpoints.USUARIOS;
import static helper.ServiceHelper.matchesJsonSchema;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Usuario;
import dataFactory.DynamicFactory;
import helper.BaseTest;

public class JsonSchemaUsuarioGetId extends BaseTest {
	private String idUsuario;
	public Response response;
	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;	
		RestAssured.basePath = "";
		
	}
	@Test
	public void deveValidarSchemaNoRetornoDeUmUsuarioComId() 
	{
		
Usuario usuario = DynamicFactory.generateRandomUsuario("true");
		
		Response response1 = usuariosServiceInherit.post(USUARIOS, usuario);
		
		idUsuario= response1.path( "_id");
		assertEquals("201", Integer.toString(response1.getStatusCode()));
		
		Response response = when().get("http://localhost:3000/usuarios/"+idUsuario); 
		assertThat(response.asString(), matchesJsonSchema("usuarios", "get_id", 200));
		
	}
	@AfterEach
	public void removerUsuarioDasValidacoes(){
		response = usuariosServiceInherit.delete(USUARIOS +"/"+ idUsuario);
	 }
}
