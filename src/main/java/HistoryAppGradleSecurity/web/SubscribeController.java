package HistoryAppGradleSecurity.web;

import HistoryAppGradleSecurity.model.binding.UserLoginBindingModel;
import HistoryAppGradleSecurity.model.binding.UserSubscribeBindingModel;
import HistoryAppGradleSecurity.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")

public class SubscribeController {

    private final UserService userService;
    private final SecurityContextRepository securityContextRepository;

    private final ModelMapper modelMapper;

    public SubscribeController(UserService userService, SecurityContextRepository securityContextRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.securityContextRepository = securityContextRepository;
        this.modelMapper = modelMapper;
    }


    @ModelAttribute("userSubscribeBindingModel")
    public UserSubscribeBindingModel userSubscribeBindingModel() {
        return new UserSubscribeBindingModel();
    }

    @ModelAttribute("userLoginBindingModel")
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/subscribe")
    public String subscribe(Model model) {
        if (!model.containsAttribute("userSubscribeBindingModel")) {
            model.addAttribute("userSubscribeBindingModel", new UserSubscribeBindingModel());
        }
        return "subscribe";
    }



    @PostMapping("/subscribe")
    public String subscribeConfirm(@Valid UserSubscribeBindingModel userSubscribeBindingModel,
                                   HttpServletRequest request,
                                   HttpServletResponse response,BindingResult bindingResult,RedirectAttributes redirectAttributes) {

                if (bindingResult.hasErrors() || !userSubscribeBindingModel.getPassword()
                .equals(userSubscribeBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("subscribeBindingModel", userSubscribeBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.subscribeBindingModel", bindingResult);

            return "redirect:/users/subscribe";
        }


        userService.subscribeUser(userSubscribeBindingModel, successfulAuth -> {
            SecurityContextHolderStrategy strategy = SecurityContextHolder.getContextHolderStrategy();

            SecurityContext context = strategy.createEmptyContext();
            context.setAuthentication(successfulAuth);

            strategy.setContext(context);
            securityContextRepository.saveContext(context, request, response);
        });

        return "redirect:/login";
    }

}
