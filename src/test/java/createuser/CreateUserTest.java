package createuser;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import stellarburgers.api.UserClient;
import stellarburgers.api.model.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateUserTest {

    //Создать userClient
    private UserClient userClient = new UserClient();

  @Test
  @DisplayName("Create a new user")
  @Description("Create new user and check answer that code is 200 success is true")
  //создать уникального пользователя
  public void userCanBeRegisterWithUniqueCredentials(){
      //Создать объект user - экземпляр класса со случайными данными
      User user = User.getRandomUser();
      //Вызвать метод создания пользователя, сохранить ответ
      // проверить код ответа
      Response response = userClient.create(user);
      boolean isUserRegistered = response
        .then()
        .assertThat().statusCode(200)
        .extract()
        .path("success");
      //Проверить, что в ответе success: true, если нет - вывести сообщение
        assertTrue(isUserRegistered, "User not created");
  }
}
