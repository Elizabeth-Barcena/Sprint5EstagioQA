package model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class Produtos {
	private String nome;
	private Integer preco;
	private String descricao;
	private Integer quantidade;
	@JsonProperty(value= "_id", access = Access.WRITE_ONLY)
	private String id;


}
