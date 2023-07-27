/*package usuario;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.response.Response;
import static helper.ServiceHelper.matchesJsonSchema;
public class JsonSchemaUsuario {
	
	@Test
	public void deveValidarSchemaNoRetornoDeListaDeUsuarios() {
		Response response = when().get("http://localhost:3000/usuarios"); 
		assertThat(response.asString(), matchesJsonSchema("usuarios", "get", 200));	
	}
}*/
