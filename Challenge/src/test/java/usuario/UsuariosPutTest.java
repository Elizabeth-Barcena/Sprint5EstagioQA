package usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import model.Usuario;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import dataFactory.DynamicFactory;
import helper.BaseTest;
import static constants.Endpoints.USUARIOS;
import java.util.Date;
@Epic("Endpoint/usuarios utilizando o verbo PUT")
public class UsuariosPutTest extends BaseTest {

	public String idUsuario;
	public Response response;
	
	@Test
	@Description("Cria usuário não existente atraves do Put")
	public void CriarUsuárioAtravésdoPut() {
		Date date = new Date();
		String id = date.getTime() +  "usuario";
		
		Usuario usuario = DynamicFactory.generateRandomUsuario("true");
		
		Response response = usuariosServiceInherit.put(USUARIOS+"/"+ id, usuario);
		idUsuario = response.path( "_id");
		assertThat(response.statusCode(), is(201));
		assertThat(response.path("message"), equalTo("Cadastro realizado com sucesso"));
				
	}

	//Estou rodando varias vezes depois de todos os testes
	@AfterEach
	public void removerUsuarios(){
		if(idUsuario!=null) {
			if(idUsuario!=null) {
				response = rest.delete(USUARIOS +"/"+ idUsuario);
			}
		}
	}
}
