package HistoryAppGradleSecurity.service.impl;

import HistoryAppGradleSecurity.model.binding.UserSubscribeBindingModel;
import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.entity.UserRoleEnt;
import HistoryAppGradleSecurity.model.enums.UserRoleEnum;
import HistoryAppGradleSecurity.model.service.UserServiceModel;
import HistoryAppGradleSecurity.model.view.UserViewModel;
import HistoryAppGradleSecurity.repository.UserRepository;
import HistoryAppGradleSecurity.repository.UserRoleRepository;
import HistoryAppGradleSecurity.service.UserService;
import HistoryAppGradleSecurity.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    private final UserRoleRepository userRoleRepository;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, LoggedUser loggedUser, ModelMapper modelMapper, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void subscribeUser(UserSubscribeBindingModel userSubscribeBindingModel,
                              Consumer<Authentication>successfulLoginProcessor) {
        UserEnt userEntity = new UserEnt().
                setFullName(userSubscribeBindingModel.getFullName()).
                setUsername(userSubscribeBindingModel.getUsername()).
                setEmail(userSubscribeBindingModel.getEmail()).
                setPassword(passwordEncoder.encode(userSubscribeBindingModel.getPassword()));

        userRepository.save(userEntity);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userSubscribeBindingModel.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        successfulLoginProcessor.accept(authentication);
    }

    @Override
    public UserEnt findCurrentUserLoginEntity() {
        return userRepository.findUserEntByUsername(loggedUser.getUsername())
                .orElse(null);
    }

    @Override
    public UserViewModel getUserProfile() {
        String username = loggedUser.getUsername();
        UserEnt user = userRepository.findUserEntByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " was not found!"));

        return modelMapper.map(user,UserViewModel.class);
    }

    @Override
    public boolean userNameExists(String username) {
        return userRepository.findUserEntByUsername(username).isPresent();
    }

    @Override
    public void registerAndLoginUser(UserServiceModel userServiceModel) {
        UserEnt newUser = modelMapper.map(userServiceModel, UserEnt.class);
        newUser.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

        UserRoleEnt userRole = userRoleRepository.
                findUserRoleEntityByRole(UserRoleEnum.USER).orElseThrow(
                        () -> new IllegalStateException("USER role not found. Please seed the roles."));

        newUser.addRole(userRole);

        newUser = userRepository.save(newUser);

        UserDetails principal = userDetailsService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

//    @Override
//    public void login(UserLoginBindingModel userLoginBindingModel) {
//        String username = userLoginBindingModel.getUsername();
//
//        UserEnt user = this.userRepository
//                .findByUsername(username)
//                .orElseThrow(() -> new IllegalStateException("User with username: [" + username + "] is not present"));
//
//        boolean passwordMatch = passwordEncoder.matches(userLoginBindingModel.getPassword(),
//                user.getPassword());
//
//        if (!passwordMatch){
//            throw new IllegalStateException("User entered incorrect password");
//        }
//
//        loggedUser.setUsername(user.getUsername())
//                .setLogged(true)
//                .setRoles(user.getRoles());
//    }



    @Override
    public UserEnt findByName(String username) {

        return userRepository.findUserEntByUsername(username)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void logout() {
        loggedUser.reset();

    }

    @Override
    public UserViewModel findBId(Long id) {
        return userRepository.findById(id).
                map(user -> modelMapper.map(user,UserViewModel.class))
                .orElse(null);
    }

//    @Override
//    public void seedUsers() {
//        if (userRepository.count() == 0) {
//
//            UserRoleEnt adminRole = new UserRoleEnt().setRole(UserRoleEnum.ADMIN);
//            UserRoleEnt userRole = new UserRoleEnt().setRole(UserRoleEnum.USER);
//            UserRoleEnt moderatorRole = new UserRoleEnt().setRole(UserRoleEnum.MODERATOR);
//
//            userRoleRepository.saveAll(List.of(adminRole, userRole));
//
//            UserEnt admin = new UserEnt().setUsername("admin").setFullName("Admin Adminov").
//                    setPassword(passwordEncoder.encode("pako"));
//            UserEnt moderator = new UserEnt().setUsername("moderator").setFullName("Moderator Modev")
//                    .setPassword(passwordEncoder.encode("pako"));
//            UserEnt user = new UserEnt().setUsername("user").setFullName("Bai Ivan").
//                    setPassword(passwordEncoder.encode("pako"));
//            admin.setRoles(Set.of(adminRole, userRole));
//            moderator.setRoles(Set.of(moderatorRole,userRole));
//            user.setRoles(Set.of(userRole));
//
//            userRepository.saveAll(List.of(admin, user,moderator));
//        }
//    }
}


