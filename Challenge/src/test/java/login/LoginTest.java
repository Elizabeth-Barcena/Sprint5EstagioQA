package login;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import dataFactory.DynamicFactory;
import helper.BaseTest;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import model.Usuario;
import static constants.Endpoints.USUARIOS;
import static constants.Endpoints.LOGIN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LoginTest extends BaseTest{
		public String idUsuario;

		public Response response;
		@Test
		@Description("Loga com usuario não existente")
		public void LogarcomUsuarioNaoExistente() {
			Date date = new Date();
			String email = date.getTime() + "@qa.com.br";
			
			Usuario usuario = DynamicFactory.generateRandomUsuario("true");
			
			Response response = usuariosServiceInherit.post(USUARIOS, usuario);
			idUsuario = response.path( "_id");
			
			Map<String, String> usuarioLogin = new HashMap<>();
			usuarioLogin.put("email", email);
			usuarioLogin.put("password", usuario.getPassword());
					
			
			Response response2 = loginServiceInherit.post(LOGIN, usuarioLogin);
			assertThat(response2.statusCode(), is(401));
			assertThat(response2.path("message"), equalTo("Email e/ou senha inválidos"));
		}
		@Test
		public void LoginComSenhaIncorreta() {

			Usuario usuario = DynamicFactory.generateRandomUsuario("true");
			
			Response response = usuariosServiceInherit.post(USUARIOS, usuario);
			idUsuario = response.path( "_id");
			
			Date date = new Date();	
			String senhaIncorreta = date.getTime() + "senhaIncorreta";
			
			Map<String, String> usuarioLogin = new HashMap<>();
			usuarioLogin.put("email", usuario.getEmail());
			usuarioLogin.put("password", senhaIncorreta);
					
			Response response2 = loginServiceInherit.post(LOGIN, usuarioLogin);
			assertThat(response2.statusCode(), is(401));
			assertThat(response2.path("message"), equalTo("Email e/ou senha inválidos"));
		  }
		
		@Test
		public void LogarUsuarioValido() {
			Usuario usuario = DynamicFactory.generateRandomUsuario("true");
			
			Response response = usuariosServiceInherit.post(USUARIOS, usuario);
			idUsuario = response.path( "_id");
		
			Map<String, String> usuarioLogin = new HashMap<>();
			usuarioLogin.put("email", usuario.getEmail());
			usuarioLogin.put("password", usuario.getPassword());
				
			Response response2 = loginServiceInherit.post(LOGIN, usuarioLogin);
			assertThat(response2.statusCode(), is(200));
			assertThat(response2.path("message"), equalTo("Login realizado com sucesso"));
		  }
		
		@Test
		public void LogarSemSenha() {
			Usuario usuario = DynamicFactory.generateRandomUsuario("true");
			
			Response response = usuariosServiceInherit.post(USUARIOS, usuario);
			idUsuario = response.path( "_id");
				
			String senha = "";
			Map<String, String> usuarioLogin = new HashMap<>();
			usuarioLogin.put("email", usuario.getEmail());
			usuarioLogin.put("password", senha);
				
			Response response2 =loginServiceInherit.post(LOGIN, usuarioLogin);
			assertThat(response2.statusCode(), is(400));
		  }
		  

		@Test
		public void LogarSemEmail() {
			Usuario usuario = DynamicFactory.generateRandomUsuario("true");
			
			Response response = usuariosServiceInherit.post(USUARIOS, usuario);
			idUsuario = response.path( "_id");
			
			String email="";
			
			Map<String, String> usuarioLogin = new HashMap<>();
			usuarioLogin.put("email", email);
			usuarioLogin.put("password", usuario.getPassword());
			
			Response response2 = loginServiceInherit.post(LOGIN, usuarioLogin);
			assertThat(response2.statusCode(), is(400));
		  }

		@Test
		public void LogarSemSenhaComEspaço() {
			Usuario usuario = DynamicFactory.generateRandomUsuario("true");
			
			Response response = usuariosServiceInherit.post(USUARIOS, usuario);
			idUsuario = response.path( "_id");
			
			String senha=" ";	
			Map<String, String> usuarioLogin = new HashMap<>();
			usuarioLogin.put("email", usuario.getEmail());
			usuarioLogin.put("password", senha);
				
			Response response2 = loginServiceInherit.post(LOGIN, usuarioLogin);
			assertThat(response2.statusCode(), is(401));
		  }

		@AfterEach
		public void removerUsuarios(){
			if(idUsuario!=null) {
				response = usuariosServiceInherit.delete(USUARIOS +"/"+ idUsuario);
				 }
		 }

}
