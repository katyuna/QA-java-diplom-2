package getuserorders;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import stellarburgers.api.OrderClient;
import stellarburgers.api.UserClient;
import stellarburgers.api.model.Ingredients;
import stellarburgers.api.model.User;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class GetUsersOrdersWithAuthTest {


    //Создать userClient
    private UserClient userClient = new UserClient();

    //Создать orderClient
    private OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Get orders for auth user")
    @Description("Get orders for auth user and check answer that code is 200 success is true")

    public void orderCanBeGetForAuthUser() {
        //Создать данные и зарегистрировать пользователя
        User user = User.getRandomUser();
        Response responseRegisterUser = userClient.create(user);
        //Извлечь из ответа токен
        String token = responseRegisterUser
                .then()
                .extract()
                .path("accessToken");
        String clearToken = token.replace("Bearer ", "");
        /*КОММЕНТАРИЙ ДЛЯ РЕВЬЮЕРА
        для проверки работы метода не обязательно создавать заказ
        т.к. если у пользователя нет заказов, то все равно должен вернуться
        "success": true, в этом случае "orders": будет пустой.
        Для полной проверки можно создать несколько заказов (ниже закомментировано)
        и в проледующем проверить, что "ingredients" не пустой, но
        на мой взгляд здесь это избыточно
        (правильность формирования списка заказов пользователя лучше проверить
        отдельным тестом)

        //Получить список ингридиентов
      Response responseGetIngredients = orderClient.getIngredients(clearToken);
        ArrayList<String> ingredients = responseGetIngredients
                .then()
                .extract()
                .path("data._id");
        //Создать заказ из ингридиеднтов
        Ingredients ingredientsInOrder = new Ingredients(ingredients);
        Response responseCreateOrder = orderClient.createOrder(clearToken, ingredientsInOrder);*/

        //Получить список заказов
        Response responseGetUserOrders = orderClient.getUserOrders(clearToken);
        boolean isOrders = responseGetUserOrders
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("success");
        assertTrue(isOrders);
    }
}
