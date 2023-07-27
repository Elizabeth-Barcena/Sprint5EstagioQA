/*package usuario;

import static org.hamcrest.Matchers.equalTo;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import static io.restassured.RestAssured.given;

import model.Usuario;


import com.github.tomakehurst.wiremock.extension.responsetemplating.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WiremockUsuariosPostTest {
	
	@RegisterExtension
	static WireMockExtension wiremock = WireMockExtension.newInstance()
		.options(wireMockConfig().
				port(9999).
				extensions(new ResponseTemplateTransformer(true))).
		build();
	@Test
	public void createsNewUserTest() {
		
		Usuario usuario = new Usuario();
		
		usuario.setNome("Mr Potato Head");
		usuario.setEmail("Mrpotatohead@pixar.com");
		usuario.setPassword("Qwarty123");
		usuario.setAdministrador("true");
		given().
			body(usuario).
		when().
			post("http://localhost:9999/usuarios").
			then().
				log().all().
			and().
				assertThat().
				statusCode(201).
			and().
				body("nome", equalTo(usuario.getNome()))
				.and()
				.body("email", equalTo(usuario.getEmail()));
	}
}
*/