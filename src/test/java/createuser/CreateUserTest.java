package createuser;

import org.junit.Test;
import stellarburgers.api.UserClient;
import stellarburgers.api.model.User;

public class CreateUserTest {

  private UserClient userClient;

  @Test
  //создать уникального пользователя
  public void userCanBeRegisterWithUniqueCredentials(){
      //Создать объект user - экземпляр класса со случайными данными
      User user = User.getRandomUser();
      System.out.println(user.getEmail());
      System.out.println(user.getPassword());
      System.out.println(user.getName());
      //boolean isUserRegistered = userClient.create(user);
      //System.out.println(isUserRegistered);

  }

}
