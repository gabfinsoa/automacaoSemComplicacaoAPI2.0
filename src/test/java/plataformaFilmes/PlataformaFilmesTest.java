package plataformaFilmes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlataformaFilmesTest {

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

    public Response post(Object jsonBody, ContentType contentType, String endpoint) {
        return RestAssured.given()  //Retorna o RESPONSE
                .relaxedHTTPSValidation() //Passa por HTTPS e Certificados
                .contentType(contentType)
                .body(jsonBody)
                .when()
                .post(endpoint)  //ENDPOINT
                .thenReturn();   //Retorna o RESPONSE
    }
}
