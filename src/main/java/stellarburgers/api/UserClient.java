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

    //Получение данных пользователя
    @Step("Get user data")
    public Response getUserInfo(String token){
        return given()
                //при создании юзера использовать базовую спецификацию, которая описана в RestClient
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .get(PATH+"user");
    }

    //Авторизация
    @Step("Login user")
    public Response loginUser(UserCredentials userCredentials){
        return given()
                //при авторизации юзера использовать базовую спецификацию, которая описана в RestClient
                .spec(getBaseSpec())
                .and()
                .body(userCredentials)
                .when()
                .post(PATH+"login");
    }
}
