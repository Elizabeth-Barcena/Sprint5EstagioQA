package services;
import static constants.Endpoints.PRODUTOS;
import java.util.List;
import java.util.Locale;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import model.Produtos;

public class ProdutosServiceInherit extends BaseRest{
	private static Faker faker = new Faker(Locale.ENGLISH);
	BaseRest rest = new BaseRest();
	public Response postProd(String token) {
		Produtos produto = new Produtos();	
		produto.setNome(faker.commerce().productName());
		produto.setPreco(faker.number().numberBetween(0, 10000));
	    produto.setDescricao(faker.lorem().sentence());
	    produto.setQuantidade(faker.number().randomDigit());
	    System.out.println("Chegou aquii");
		return postProdutos(PRODUTOS, produto, token);
	}
	
	public Response getProdutos(String id) {
		return get(PRODUTOS + id);
	}
	public Response getProdutos() {
		System.out.println("Chega");
		return get(PRODUTOS);
	}
	public List<Produtos> getProd(){
		return get(PRODUTOS).jsonPath().getList(PRODUTOS, Produtos.class);
	}
	public List<Produtos> getProd(String id){
		return get(PRODUTOS+ id).jsonPath().getList(PRODUTOS, Produtos.class);
	}
	

}
