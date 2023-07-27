package login;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import dataFactory.DynamicFactory;
import helper.BaseTest;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.HashMap;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import model.Usuario;

import static constants.Endpoints.LOGIN;
import static constants.Endpoints.USUARIOS;
import static helper.ServiceHelper.matchesJsonSchema;
public class JsonSchemaLogin extends BaseTest {
	public Response response;
	static class idUsuario{
	    static String idUser;
	    
	}
	static class usuarioLogin{
		String nome;
		String email;
	}
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
	public void deveValidarOLogin() {
			Response response = when().post("http://localhost:3000/login"); 
			Usuario usuario = DynamicFactory.generateRandomUsuario("true");
			
			Response response2 = usuariosServiceInherit.post(USUARIOS, usuario);
			idUsuario.idUser = response2.path( "_id");
			
			Map<String, String> usuarioLogin = new HashMap<>();
			usuarioLogin.put("email", usuario.getEmail());
			usuarioLogin.put("password", usuario.getPassword());
			
			Response response3 = loginServiceInherit.post(LOGIN, usuarioLogin);
			
			assertEquals("200", Integer.toString(response3.getStatusCode()));
			assertThat(response.asString(), matchesJsonSchema("login", "post", 200));
	}
	@AfterEach
	public void removerUsuarios(){
		if(idUsuario.idUser!=null) {
			response = usuariosServiceInherit.delete(USUARIOS +"/"+ idUsuario.idUser);
			 }
	 }
}
