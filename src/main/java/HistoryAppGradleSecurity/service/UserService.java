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


    UserEnt findCurrentUserLoginEntity();
   UserViewModel getUserProfile();

    boolean userNameExists(String username);

    void registerAndLoginUser(UserServiceModel userServiceModel);


    UserEnt findByName(String username);

    void logout();

    UserViewModel findBId(Long id);

//    void seedUsers();
}
