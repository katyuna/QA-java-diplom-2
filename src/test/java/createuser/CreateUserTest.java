package createuser;

import org.junit.Test;

import stellarburgers.api.UserClient;
import stellarburgers.api.model.User;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateUserTest {
    //Создать userClient
    private UserClient userClient = new UserClient();

  @Test
  //создать уникального пользователя
  public void userCanBeRegisterWithUniqueCredentials(){
      //Создать объект user - экземпляр класса со случайными данными
      User user = User.getRandomUser();
      boolean isUserRegistered = userClient.create(user);
      //Проверить, что в ответе success: true, если нет - вывести сообщение
      assertTrue(isUserRegistered, "User not created");
  }
}
