package usuario;
import static constants.Endpoints.USUARIOS;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import services.BaseRest;
import model.Usuario;
import dataFactory.DynamicFactory;
import helper.BaseTest;
import io.qameta.allure.Epic;

@Epic("Endpoint/usuarios utilizando o verbo POST")
public class UsuariosPostTest extends BaseTest{
	
	//private static RequestSpecification reqSpec;
	private static BaseRest rest;
	public String idUsuario;

	public Response response;
	//Roda apenas uma vez antes de todos os testes
	@BeforeAll
	public static void setup() {
				rest = new BaseRest();
	}
	@Test
	@Description("Cria usuario")
	public void CriarUsuário() {
		Usuario usuario = DynamicFactory.generateRandomUsuario("true");
		
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		assertThat(response.statusCode(), is(201));
		assertThat(response.path("message"), equalTo("Cadastro realizado com sucesso"));
	}

	@Test
	@Description("Cria usuario com nome em branco")
	public void CriarUsuárioNomeEmBranco() {
		Usuario usuario = DynamicFactory.gerarUsuarioComNomeEmBranco("true");
		
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		assertThat(response.statusCode(), is(400));
		
	}
	
	@Test
	@Description("Cria usuario com email em branco")
	public void CriarUsuárioEmailEmBranco() {
		Usuario usuario = DynamicFactory.gerarUsuarioComEmailEmBranco("true");
	
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		assertThat(response.statusCode(), is(400));
		
	}

	@Test
	@Description("Cria usuario com senha em branco")
	public void CriarUsuárioPasswordEmBranco() {
		Usuario usuario = DynamicFactory.gerarUsuarioComSenhaEmBranco("true");
	
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		assertThat(response.statusCode(), is(400));
		
	}

	@Test
	@Description("Cria usuario com administrador em branco")
	public void CriarUsuárioAdmEmBranco() {
		Usuario usuario = DynamicFactory.gerarUsuarioComAdminEmBranco();
	
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		assertThat(response.statusCode(), is(400));
		
	}
	
	@Test
	@Description("Cria usuario com email de provedor gmail")
	public void CriarUsuarioComGmail() {
		Usuario usuario = DynamicFactory.gerarUsuarioComGmail("true");
		
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		assertThat(response.statusCode(), is(400));
		assertThat(response.path("message"), equalTo("Cadastro realizado com sucesso"));
	}
	
	@Test
	@Description("Cria usuario com email de provedor hotmail")
	public void CriarUsuarioComHotmail() {
		Usuario usuario = DynamicFactory.gerarUsuarioComHotmail("true");
		
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		assertThat(response.statusCode(), is(400));
		assertThat(response.path("message"), equalTo("Cadastro realizado com sucesso"));
	}
	
	@Test
	@Description("Cria usuario com email invalido")
	public void CriarUsuarioComEmailInvalido() {
		Usuario usuario = DynamicFactory.gerarUsuarioComEmailInvalido("true");
		
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		assertThat(response.statusCode(), is(400));
		
	}
	
	@Test
	@Description("Criar usuario com senha abaixo de 5 caracteres")
	public void SenhaAbaixoDe5() {
		Usuario usuario = DynamicFactory.gerarUsuarioComSenhaAbaixoDe5("true");
		Response response = usuariosServiceInherit.post(USUARIOS, usuario);
		idUsuario = response.path( "_id");
		assertThat(response.statusCode(), is(201));
		assertThat(response.path("message"), equalTo("Cadastro realizado com sucesso"));

	}
	
	  @Test
	  @Description("Criar usuario com senha acima de 10 caracteres")
	public void SenhaAcimaDe10() {
		  	Usuario usuario = DynamicFactory.gerarUsuarioComSenhaAcimaDe10("true");
		  	
			Response response = usuariosServiceInherit.post(USUARIOS, usuario);
			idUsuario = response.path( "_id");
			assertThat(response.statusCode(), is(201));
			assertThat(response.path("message"), equalTo("Cadastro realizado com sucesso"));
		}
	
	//Estou rodando varias vezes depois de todos os testes
	  @AfterEach
		public void removerUsuarioDasValidacoes(){
		  if(idUsuario!=null) {
		response = rest.delete(USUARIOS +"/"+ idUsuario);
		 }
	  }

}
