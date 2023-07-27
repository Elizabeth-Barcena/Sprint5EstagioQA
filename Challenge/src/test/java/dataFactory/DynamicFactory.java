package dataFactory;

import java.util.Locale;
import model.Carrinho;
import model.Produtos;
import model.Usuario;

import com.github.javafaker.Faker;
import helper.BaseTest;

public class DynamicFactory extends BaseTest {
	private static Faker faker = new Faker(Locale.ENGLISH);
	
	public static Usuario generateRandomUsuario(String isAdmin) {
		Usuario usuario = new Usuario();
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setPassword(faker.internet().password());
		usuario.setAdministrador(isAdmin);
		return usuario;
	}
	
	public static Usuario gerarUsuarioComNomeEmBranco(String isAdmin) {
		Usuario usuario = new Usuario();
		usuario.setNome("");
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setPassword(faker.internet().password());
		usuario.setAdministrador(isAdmin);
		return usuario;
	}
	public static Usuario gerarUsuarioComEmailEmBranco(String isAdmin) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(faker.name().fullName());
		usuario.setEmail("");
		usuario.setPassword(faker.internet().password());
		usuario.setAdministrador(isAdmin);
		return usuario;
	}
	public static Usuario gerarUsuarioComSenhaEmBranco(String isAdmin) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setPassword("");
		usuario.setAdministrador(isAdmin);
		return usuario;
	}
	public static Usuario gerarUsuarioComAdminEmBranco() {
		Usuario usuario = new Usuario();
		
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setPassword(faker.internet().password());
		usuario.setAdministrador("");
		return usuario;
	}
	public static Usuario gerarUsuarioComGmail(String isAdmin) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(faker.name().fullName());
		usuario.setEmail("RestAssured@gmail.com");
		usuario.setPassword(faker.internet().password());
		usuario.setAdministrador(isAdmin);
		return usuario;
	}
	public static Usuario gerarUsuarioComHotmail(String isAdmin) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(faker.name().fullName());
		usuario.setEmail("RestAssured@hotmail.com");
		usuario.setPassword(faker.internet().password());
		usuario.setAdministrador("true");
		return usuario;
	}
	public static Usuario gerarUsuarioComEmailInvalido(String isAdmin) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.name().fullName());
		usuario.setPassword(faker.internet().password());
		usuario.setAdministrador(isAdmin);
		return usuario;
	}
	public static Usuario gerarUsuarioComSenhaAbaixoDe5(String isAdmin) {

		Usuario usuario = new Usuario();
		
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setPassword("tes");
		usuario.setAdministrador(isAdmin);
		return usuario;
	}
	public static Usuario gerarUsuarioComSenhaAcimaDe10(String isAdmin) {

		Usuario usuario = new Usuario();
		
		usuario.setNome(faker.name().fullName());
		usuario.setEmail(faker.internet().emailAddress());
		usuario.setPassword("testeeeeeeeeeeeeeeeeeeeee");
		usuario.setAdministrador(isAdmin);
		return usuario;
	}
	
	//PRODUTOS
	
	public static Produtos generateRandomProduto(){
		Produtos produto = new Produtos();	
		produto.setNome(faker.commerce().productName());
		produto.setPreco(faker.number().numberBetween(0, 10000));
	    produto.setDescricao(faker.lorem().sentence());
	    produto.setQuantidade(10);
	    return produto;
	}
	public static Produtos gerarProdutoSemNome(){
		Produtos produto = new Produtos();	
		produto.setNome("");
		produto.setPreco(faker.number().numberBetween(0, 10000));
	    produto.setDescricao(faker.lorem().sentence());
	    produto.setQuantidade(faker.number().randomDigitNotZero());
	    return produto;
	}
	public static Produtos gerarProdutoComPrecoNull(){
		Produtos produto = new Produtos();	
		produto.setNome(faker.commerce().productName());
		produto.setPreco(null);
        produto.setDescricao(faker.lorem().sentence());
        produto.setQuantidade(faker.number().randomDigitNotZero());
	    return produto;
	}
	public static Produtos gerarProdutoSemDescricao(){
		Produtos produto = new Produtos();	
		produto.setNome(faker.commerce().productName());
		produto.setPreco(faker.number().numberBetween(0, 10000));
        produto.setDescricao("");
        produto.setQuantidade(faker.number().randomDigitNotZero());
	    return produto;
	}

	public static Produtos gerarProdutoComQuantidadeNula(){
		Produtos produto = new Produtos();	
		produto.setNome(faker.commerce().productName());
		produto.setPreco(faker.number().numberBetween(0, 10000));
        produto.setDescricao(faker.lorem().sentence());
        produto.setQuantidade(null);
	    return produto;
	}
	public static Produtos gerarProdutoComTodosOsCamposNulos() {
		Produtos produto = new Produtos();	
		produto.setNome(null);
		produto.setPreco(null);
        produto.setDescricao(null);
        produto.setQuantidade(null);
        return produto;
	}
	public static Carrinho gerarCarrinho(String id, int quantidade) {
		
		Carrinho carrinho = new Carrinho();
		
		return carrinho;
	}
	
}
