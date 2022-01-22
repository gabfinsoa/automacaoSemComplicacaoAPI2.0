package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class RestUtils {

    private static Response response;

    public static Response getResponse() {
        return response;
    }

    public  static void setBaseURI (String uri) {
        RestAssured.baseURI = uri;
    }

    public static String getBaseURI () {
        return RestAssured.baseURI;
    }

    public static Response post(Object jsonBody, ContentType contentType, String endpoint) {
        return response = RestAssured.given()  //Retorna o RESPONSE
                .relaxedHTTPSValidation() //Passa por HTTPS e Certificados
                .contentType(contentType)
                .body(jsonBody)
                .when()
                .post(endpoint)  //ENDPOINT
                .thenReturn();   //Retorna o RESPONSE
    }

    public static Response get(Map<String, String> header, String endpoint) {
        return response = RestAssured.given()
                .relaxedHTTPSValidation()
                .headers(header)  //HEADERS com "s" para poder enviar o MAP (chave, valor)
                .when()
                .get(endpoint)
                .thenReturn();
    }
}
