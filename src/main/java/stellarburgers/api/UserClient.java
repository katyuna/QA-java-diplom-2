package stellarburgers.api;

import stellarburgers.api.model.User;
import stellarburgers.api.model.UserCredentials;

import static io.restassured.RestAssured.given;

//Выносим общую часть URL в отдельный класс RestClient и наследуем от него класс UserClient
public class UserClient extends RestClient{

    public final String PATH = BASE_URL+"auth/";  //URL
    //Метод регистрации нового пользователя
    public boolean create(User user){
        return given()
                //при создании юзера использовать базовую спецификацию, которая описана в RestClient
                .spec(getBaseSpec())
                .and()
                .body(user)
                .when()
                .post(PATH+"register")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("success");
    }

    //Метод авторизации пользователя
    public String login(UserCredentials userCredettials){
        return given()
                //при авторизхации юзера использовать базовую спецификацию, которая описана в RestClient
                .spec(getBaseSpec())
                .and()
                .body(userCredettials)
                .when()
                .post(PATH+"login")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("accessToken");
    }
}
