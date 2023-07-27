package services;

import static constants.Endpoints.CARRINHO;
import java.util.List;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import model.Carrinho;


@NoArgsConstructor
public class CarrinhoService extends BaseRest {
	
	public Response getCarrinho() {
		return get(CARRINHO);
	}
	public Response getCarrinho(String id) {
		return get(CARRINHO+ id);
	}
	public Response postCarrinho(String token, String payload) {
		return postProdutos(CARRINHO ,payload, token);
	}
	
	public List<Carrinho> getCarrinhos(){
		return get(CARRINHO).jsonPath().getList(CARRINHO, Carrinho.class);
	}
	public List<Carrinho> getCarrinhos(String id){
		return get(CARRINHO + id).jsonPath().getList(CARRINHO, Carrinho.class);
	}
	public Carrinho car() {
		Carrinho car = new Carrinho();
		return car;
	}

}
