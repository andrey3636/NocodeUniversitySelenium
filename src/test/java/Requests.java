import java.util.Random;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Random;

import static io.restassured.RestAssured.given;

// Класс формирования и реализации REST запросов
public class Requests {
    // Строка с базовым URI
    final static String BASE_URI = "https://studio-api.softr.io/v1/api";
    // Строка с ключом к API
    final static String API_KEY = "khIbAyJIU5CIuh1oDuBRx1s49";
    // Строка с доменом приложения
    final static String DOMAIN = "jere237.softr.app";

    // Базовая спецификация запроса
    static RequestSpecification specification = new RequestSpecBuilder()
            .setUrlEncodingEnabled(false)
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addHeader("Softr-Api-Key", API_KEY)
            .addHeader("Softr-Domain",DOMAIN )
            .build();

    // Запрос на удаление пользователя
    public Response deleteRequest(String endPoint) {

        Response response = RestAssured.given()
                .spec(specification)
                .when()
                .log().all()
                .delete(endPoint)
                .then().log().all()
                .extract().response();
        return response;
    }
}
