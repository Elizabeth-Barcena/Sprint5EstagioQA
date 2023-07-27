package services;

import static constants.Endpoints.LOGIN;
import io.restassured.response.Response;

public class LoginServiceInherit extends BaseRest{
	public Response postUsuario(String usuarioLogin) {
		return post(LOGIN, usuarioLogin);
	}

}
