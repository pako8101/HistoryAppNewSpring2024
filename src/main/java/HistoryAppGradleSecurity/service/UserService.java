package HistoryAppGradleSecurity.service;

import HistoryAppGradleSecurity.model.binding.UserLoginBindingModel;
import HistoryAppGradleSecurity.model.binding.UserSubscribeBindingModel;
import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.service.UserServiceModel;
import HistoryAppGradleSecurity.model.view.UserViewModel;
import org.springframework.security.core.Authentication;

import java.util.function.Consumer;

public interface UserService {
    void subscribeUser(UserSubscribeBindingModel userSubscribeBindingModel,
                       Consumer<Authentication> successfulLoginProcessor);


  //  void loginUser(Long id, String username);

   // UserServiceModel findByUsernameAndPassword(String username, String password);

  //  UserServiceModel findById(Long id);

    UserEnt findCurrentUserLoginEntity();
   UserViewModel getUserProfile();

    boolean userNameExists(String username);

    void registerAndLoginUser(UserServiceModel userServiceModel);

    void login(UserLoginBindingModel userLoginBindingModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);
}
