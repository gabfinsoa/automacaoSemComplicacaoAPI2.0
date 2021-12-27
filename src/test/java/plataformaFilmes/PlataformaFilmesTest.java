package plataformaFilmes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlataformaFilmesTest {

    static String token;

    @Test
    public void validarLogin() {
        RestAssured.baseURI = "http://localhost:8080/";

        String jsonBody = "{" +
                "    \"email\": \"aluno@email.com\"," +
                "    \"senha\": \"123456\"" +
                "}";

        Response response = post(jsonBody, ContentType.JSON, "auth");

        assertEquals(200, response.statusCode());
//        String pegaToken = response.body().jsonPath().get("token");  //Pega o campo TOKEN no BODY do RESPONSE
        String pegaToken = response.jsonPath().get("token"); //Sem o BODY  //Pega o campo TOKEN no BODY do RESPONSE

        System.out.println("TOKEN: " + pegaToken);
    }

    @BeforeAll
    public static void validarLoginMap() {
        RestAssured.baseURI = "http://localhost:8080/";

        Map<String, String> map = new HashMap<>(); //Tipo da CHAVE e o tipo do VALOR. Sugest.: VALOR pode ser do tipo OBJETO
        map.put("email", "aluno@email.com"); // Add novo CHAVE e VALOR
        map.put("senha", "123456");

        Response response = post(map, ContentType.JSON, "auth");

        assertEquals(200, response.statusCode());
        token = response.jsonPath().get("token");
        System.out.println(token);
    }

    @Test
    public void validarConsultaCategorias() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + token);

        Response response = get(header, "categorias"); //CTRL+ALT+M -> Cria o método. (neste caso o "private get" abaixo)

        assertEquals(200, response.statusCode());
        System.out.println(response.jsonPath().get().toString()); //Printa o JSON BODY
    }

    private Response get(Map<String, String> header, String endpoint) {
        return RestAssured.given()
                .relaxedHTTPSValidation()
                .headers(header)  //HEADERS com "s" para poder enviar o MAP (chave, valor)
                .when()
                .get(endpoint)
                .thenReturn();
    }

    public static Response post(Object jsonBody, ContentType contentType, String endpoint) {
        return RestAssured.given()  //Retorna o RESPONSE
                .relaxedHTTPSValidation() //Passa por HTTPS e Certificados
                .contentType(contentType)
                .body(jsonBody)
                .when()
                .post(endpoint)  //ENDPOINT
                .thenReturn();   //Retorna o RESPONSE
    }
}
