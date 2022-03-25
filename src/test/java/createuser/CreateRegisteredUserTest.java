package createuser;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import stellarburgers.api.UserClient;
import stellarburgers.api.model.User;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CreateRegisteredUserTest {
    //Создать userClient
    private UserClient userClient = new UserClient();
    @Test
    @DisplayName("Create registered user")
    @Description("Create user which already exist and check answer that code 403 and success is false")
    public void userCantBeRegisterWithSameCredentials() {

        //создать объект - пользователь
        User user = User.getRandomUser();
        //Зарегистрировать пользователя со случайными данными
        Response responseRegisterUser = userClient.create(user);
        //Зарегистрировать пользователя с этими же данными повторно
        Response responseRegisterTheSameUser = userClient.create(user);

        boolean isUserNotRegistered = responseRegisterTheSameUser
                .then()
                .assertThat().statusCode(403)
                .extract()
                .path("success");
        //проверить, что  код ответа 403 Forbidden и "success": false,
        // если нет - вывести сообщение
        assertFalse(isUserNotRegistered, "User is created");



        /*String token = responseRegisterUser
                .then()
                .extract()
                .path("accessToken");
        String clearToken = token.replace("Bearer ", "");

        //получить информацию о пользователе (email, name) по токену
        Response responseGetUserInfo = userClient.getUserInfo(clearToken);
        String email = responseGetUserInfo
                .then()
                .extract()
                .path("user.email");
        String name = responseGetUserInfo
                .then()
                .extract()
                .path("user.name");*/

        //зарегистрировать пользователя с полученными данными и любым паролем
        //User sameUser = new User(email, "password", name);
       // Response responseTheSameUser = userClient.create(sameUser);

        //проверить, что  код ответа 403 Forbidden и "success": false,
        // если нет - вывести сообщение
       /* boolean isUserNotRegistered = responseTheSameUser
                .then()
                .assertThat().statusCode(403)
                .extract()
                .path("success");
        assertFalse(isUserNotRegistered, "User is created");*/
    }
}
