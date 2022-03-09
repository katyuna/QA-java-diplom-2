package stellarburgers.api;

import pojo.User;
import pojo.UserCredentials;

import static io.restassured.RestAssured.given;

public class UserClient {
    //Метод регистрации нового пользователя
    public boolean create(User user){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/register")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("success");
    }

    //Метод авторизации пользователя
    public String login(UserCredentials userCredettials){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(userCredettials)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/login")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("accessToken");
    }
}
