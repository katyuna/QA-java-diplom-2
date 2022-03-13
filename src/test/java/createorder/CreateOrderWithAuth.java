package createorder;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import stellarburgers.api.OrderClient;
import stellarburgers.api.UserClient;
import stellarburgers.api.model.User;

import java.util.ArrayList;

public class CreateOrderWithAuth {

    //Создать userClient
    private UserClient userClient = new UserClient();

    //Создать orderClient
    private OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Create order with auth")
    @Description("Create order with auth and check answer that code is 200 success is true")

    public void CreateOrderCanBeCreatedWithAuth() {
        //Создать данные и зарегистрировать пользователя
        User user = User.getRandomUser();
        Response responseRegisterUser = userClient.create(user);
        //Извлечь из ответа токен
        String token = responseRegisterUser
                .then()
                .extract()
                .path("accessToken");
        String clearToken = token.replace("Bearer ", "");
        //Получить список ингридиентов
        Response responseGetIngredients = orderClient.getIngredients(clearToken);
        ArrayList<String> ingredients = responseGetIngredients
                .then()
                .extract()
                .path("data._id");
        System.out.println(ingredients);
        //Создать заказ из ингридиеднтов
        Response responseCreateOrder = orderClient.createOrder(clearToken, ingredients);
        System.out.println();


        boolean isOrderCreated = responseCreateOrder
                .then()
                .extract()
                .path("success");
        System.out.println(isOrderCreated);
    }
 }
