package services;

import helper.EnvConfig;
import lombok.Getter;
import lombok.Setter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import static io.restassured.http.ContentType.JSON;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

@Getter
@Setter
public class BaseRest {
	private RequestSpecification requestSpec;
	
	public BaseRest() {
		requestSpec = getSpecBuilder().build();
}
	public Response get(String endpoint) {
		return 
		given().
			spec(requestSpec).
			log().all().
		when().
			get(endpoint).
		then().
			log().body().
			log().status().
			extract().
			response();
	}
	public Response post(String endpoint, Object payload) {
		return
		
		  given().
			spec(requestSpec).
			contentType(JSON).
			body(payload).
		when().
			post(endpoint).
		then().
			log().body().
			log().status().
			extract().
			response();
	}

	public Response postProdutos(String endpoint, Object payload, String token) {
		
		return
			given().
			spec(requestSpec).
				header("authorization", token).
				body(payload).
				contentType(ContentType.JSON).
			when().
				post(endpoint).
			then().
			log().body().
			log().status().
			extract().
			response();
	}

	public Response put(String endpoint, Object payload) {
		return		
		given().
					spec(requestSpec).
			body(payload).
			contentType(ContentType.JSON).
		when().
			put(endpoint).
		then().
			log().body().
			log().status().
			extract().
			response();
	}
	public Response putProdutos(String endpoint, Object payload, String token) {
		return		
		given().
				spec(requestSpec).
				header("authorization", token).
			body(payload).
			contentType(ContentType.JSON).
		when().
			put(endpoint).
		then().
			log().body().
			log().status().
			extract().
			response();
	}
	public Response delete(String endpoint) {
		return
				
		(Response) given().
		spec(requestSpec).
	    when().
	    	delete(endpoint).
	    then().
	    log().body().
		log().status().
	    extract().
		response();
		
	}
	public Response deleteProdutos(String endpoint, String token) {
		return
				
		(Response) given().
		spec(requestSpec).
		header("authorization", token).
	    when().
	    	delete(endpoint).
	    then().
	    log().body().
		log().status().
	    extract().
		response();
		
	}

	
	public RequestSpecBuilder getSpecBuilder() {
		return new RequestSpecBuilder().
				setBaseUri(EnvConfig.getProperty("url", "")).
				setAccept(JSON);
	}

}