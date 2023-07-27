package model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
@Data
public class Usuario {
	private String nome;
	private String email;
	private String password;
	private String administrador;
	@JsonProperty(value= "_id", access = Access.WRITE_ONLY)
	private String id;
	


}
