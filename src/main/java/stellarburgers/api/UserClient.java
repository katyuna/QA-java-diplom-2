package stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import stellarburgers.api.model.User;
import stellarburgers.api.model.UserCredentials;

import static io.restassured.RestAssured.given;

//Выносим общую часть URL в отдельный класс RestClient и наследуем от него класс UserClient
public class UserClient extends RestClient{

    public final String PATH = BASE_URL+"auth/";  //URL

    @Step("Create user {user}")
    //Успешная регистрации нового пользователя
    public Response create(User user){
        return given()
                //при создании юзера использовать базовую спецификацию, которая описана в RestClient
                .spec(getBaseSpec())
                .and()
                .body(user)
                .when()
                .post(PATH+"register");
    }

    @Step("Login user {userCredentials}")
    //Метод авторизации пользователя
    public String login(UserCredentials userCredentials){
        return given()
                //при авторизхации юзера использовать базовую спецификацию, которая описана в RestClient
                .spec(getBaseSpec())
                .and()
                .body(userCredentials)
                .when()
                .post(PATH+"login")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("accessToken");
    }
}
