package produto;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.response.Response;
import static helper.ServiceHelper.matchesJsonSchema;
public class JsonSchemaProdutos {
	
	@Test
	public void deveValidarSchemaNoRetornoDeListaDeProdutos() {
		Response response = when().get("http://localhost:3000/produtos"); 
		assertThat(response.asString(), matchesJsonSchema("produtos", "get", 200));	
	}
}
