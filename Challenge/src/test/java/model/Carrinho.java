package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrinho {
	private List<ProdutosCarrinho> Produtos = new ArrayList<ProdutosCarrinho>();
	 @JsonIgnore
	    private String id;
	    
	    @JsonIgnore
	    public void addProduto(ProdutosCarrinho produtos) {
	    	this.Produtos.add(produtos);
	    }

}
