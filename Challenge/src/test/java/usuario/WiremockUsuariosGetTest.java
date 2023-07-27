/*package usuario;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;

@WireMockTest(httpPort = 9999)
public class WiremockUsuariosGetTest {
	
	@Test
	public void getUsuariosDeveRetornarListaComWiremock() {
		String expectedName = "Bruce";
		when().
			get("http://localhost:9999/usuarios").
			then().
				log().all().
			and().
				assertThat().
				statusCode(200).
			and().
				body("[0].name", equalTo(expectedName));
	}
}*/
