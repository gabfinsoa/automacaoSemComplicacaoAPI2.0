package plataformaFilmes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.RestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlataformaFilmesTest {

    static String token;

//    @Test
//    public void validarLogin() {
////        RestAssured.baseURI = "http://localhost:8080/";
//        RestUtils.setBaseURI("http://localhost:8080/");
//
//        String jsonBody = "{" +
//                "    \"email\": \"aluno@emailc.com\"," +
//                "    \"senha\": \"123456\"" +
//                "}";
//
//        RestUtils.post(jsonBody, ContentType.JSON, "auth");
//
//        assertEquals(200, RestUtils.getResponse().statusCode());
////        String pegaToken = response.body().jsonPath().get("token");  //Pega o campo TOKEN no BODY do RESPONSE
//        String pegaToken = RestUtils.getResponse().jsonPath().get("token"); //Sem o BODY  //Pega o campo TOKEN no BODY do RESPONSE
//
//        System.out.println("TOKEN: " + pegaToken);
//    }

    @BeforeAll
    public static void validarLoginMap() {
        //RestAssured.baseURI = ;
        RestUtils.setBaseURI("http://localhost:8080/");

        Map<String, String> map = new HashMap<>(); //Tipo da CHAVE e o tipo do VALOR. Sugest.: VALOR pode ser do tipo OBJETO
        map.put("email", "aluno@email.com"); // Add novo CHAVE e VALOR
        map.put("senha", "123456");

        Response response = RestUtils.post(map, ContentType.JSON, "auth");

        assertEquals(200, response.statusCode());
        token = response.jsonPath().get("token");
        System.out.println(token);
        System.out.println("RESPONSE: " + response.jsonPath().get().toString());
    }

    @Test
    public void validarConsultaCategorias() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + token);

        Response response = RestUtils.get(header, "categorias"); //CTRL+ALT+M -> Cria o método. (neste caso o "private get" abaixo)
        System.out.println("LISTA DE TODAS CATEGORIAS com IDs: " + response.jsonPath().get().toString()); //Printa o JSON BODY

        assertEquals(200, response.statusCode());

//      response.jsonPath().get();  //Pega no ID = 3 o TIPO = TERROR
        assertEquals("Terror", response.jsonPath().get("tipo[2]"));  //Valida no ID = 3 o TIPO = TERROR

        List<String> listTipo = response.jsonPath().get("tipo");
        System.out.println("Lista soh das CATEGORIAS: " + listTipo);
        assertTrue(listTipo.contains("Terror"));
    }
}
