package stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import stellarburgers.api.model.Ingredients;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {

    public final String PATH = BASE_URL + "orders";

    //Получение данных об ингридиентах
    @Step("Get ingredients")
    public Response getIngredients(String token) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .get(BASE_URL + "ingredients");
    }
    //Создание заказа
    @Step("Create order")
    public Response createOrder(String token, Ingredients ingredients) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .and()
                .body(ingredients)
                .when()
                .post(PATH);
    }

    //Получение заказов конкретного пользователя
    @Step("Get user orders")
    public Response getUserOrders(String token) {
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .get(PATH);
    }
}