package model;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutosCarrinho {
	 private String idProduto;
	 private int quantidade;
}
