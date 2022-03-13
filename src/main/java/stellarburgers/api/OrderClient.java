package stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {

    public final String PATH = BASE_URL + "orders";  //URL

    //Получение данных об ингридиентах
    @Step("Get ingredients")
    public Response getIngredients(String token) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .get(BASE_URL + "ingredients");
    }

    @Step("Create order")
    public Response createOrder(String token, ArrayList<String> ingredients) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .and()
                .body(ingredients)
                .when()
                .post(BASE_URL);
    }
}