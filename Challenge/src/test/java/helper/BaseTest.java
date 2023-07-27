package helper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import lombok.Getter;
import lombok.Setter;
import model.Carrinho;
import model.Produtos;
import model.ProdutosCarrinho;
import services.BaseRest;
import services.CarrinhoService;
import services.UsuariosServiceInherit;
import services.LoginServiceInherit;
import services.ProdutosServiceInherit;
@Getter
@Setter
public class BaseTest {
	protected BaseRest rest;
	protected UsuariosServiceInherit usuariosServiceInherit;
	protected LoginServiceInherit loginServiceInherit;
	protected ProdutosServiceInherit produtosServiceInherit;
	protected CarrinhoService carrinhoService;
	protected Produtos produto;
	protected ProdutosCarrinho produtosCarrinho;
	protected Carrinho carrinho;
	@BeforeAll
	public static void setupEnviroment() {
		
	}
	@BeforeEach
	public void instantiateServices() {
		rest = new BaseRest();
		usuariosServiceInherit = new UsuariosServiceInherit();
		loginServiceInherit = new LoginServiceInherit();
		produtosServiceInherit = new ProdutosServiceInherit();
		carrinhoService = new CarrinhoService();
		
	}

}
