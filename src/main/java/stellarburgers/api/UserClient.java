package stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import stellarburgers.api.model.User;
import stellarburgers.api.model.UserCredentials;

import static io.restassured.RestAssured.given;

//Выносим общую часть URL в отдельный класс RestClient и наследуем от него класс UserClient
public class UserClient extends RestClient{

    public final String PATH = BASE_URL+"auth/";  //URL

    //Регистрация
    @Step("Create user")
    public Response create(User user){
        return given()
                //при создании юзера использовать базовую спецификацию, которая описана в RestClient
                .spec(getBaseSpec())
                .and()
                .body(user)
                .when()
                .post(PATH+"register");
    }

   //Авторизация
    @Step("Login user")
    public Response loginUser(UserCredentials userCredentials){
        return given()
                //использовать базовую спецификацию, которая описана в RestClient
                .spec(getBaseSpec())
                .and()
                .body(userCredentials)
                .when()
                .post(PATH+"login");
    }

    //Получение данных пользователя
    @Step("Get user data")
    public Response getUserInfo(String token){
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .get(PATH+"user");
    }

    //Обновлпение данных пользователя
    @Step("Edit user data")
    public Response editUserInfo(String token, User user){
        return given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .and()
                .body(user)
                .when()
                .patch(PATH+"user");
    }
}
