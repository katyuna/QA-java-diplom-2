package createuser;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import stellarburgers.api.UserClient;
import stellarburgers.api.model.User;

import static org.junit.Assert.assertFalse;
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
      //Вызвать метод создания пользователя и из ответа созхранить значение по ключу success
      Response response = userClient.create(user);
      boolean isUserRegistered = response
        .then()
        .assertThat().statusCode(200)
        .extract()
        .path("success");
      //Проверить, что в ответе success: true, если нет - вывести сообщение
        assertTrue(isUserRegistered, "User not created");
        //Проверить наличие токена в ответе (См комметарий для ревьюера)
      String token = response
              .then()
              .extract()
              .path("accessToken");
      String clearToken = token.replace("Bearer ", "");
      assertFalse(clearToken.isEmpty());

        /* КОММЕНТАРИЙ ДЛЯ РЕВЬЮЕРА
        1) Дополнительно проверим наличие токена т.к.
        приходит токен или нет - зависит от конкретной реализации API,
        нужно смотреть в код, чтобы понять как написано API и мождет быть, что в каком-то
        конкретном случае неп приходит то,ь что должно приходить по стандартным соглашеничям
        2) Момент про удаление тестовых данных после теста
        обсудила с наставиником
        "Поскольку методов в официальной документации не описано,
        сейчас ты можешь пока опустить этот шаг."
         */
  }
}
