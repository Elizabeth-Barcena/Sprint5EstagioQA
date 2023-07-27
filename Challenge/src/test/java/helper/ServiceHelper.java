package helper;
import io.restassured.module.jsv.JsonSchemaValidator;
import java.io.InputStream;
import java.text.MessageFormat;


public class ServiceHelper {
	// Utilizado para criar o caminho para um arquivo schema json
	public static InputStream jsonSchemaStream(String endpoint, String schema, int status) {
		String path = "/schemas/{0}/{1}/{2}.json";
		path = MessageFormat.format(path,endpoint, schema, status);
		return ServiceHelper.class.getResourceAsStream(path);
		
		}
	
	// Matcher validador para validar um schema JSON de acordo com os caminhos recebidos
		public static JsonSchemaValidator matchesJsonSchema(String endpoint, String schema, int status) {
			InputStream schemaToMatch = jsonSchemaStream( endpoint,schema, status);
			return JsonSchemaValidator.matchesJsonSchema(schemaToMatch);
		}

}
