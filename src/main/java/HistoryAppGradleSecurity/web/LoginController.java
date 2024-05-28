package HistoryAppGradleSecurity.web;

import HistoryAppGradleSecurity.model.binding.UserLoginBindingModel;
import HistoryAppGradleSecurity.service.UserService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class LoginController {

private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }
    @GetMapping("/login")
    public String login( Model model) {
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }

        return "login";
    }




    @PostMapping("/login-error")
    public String onFailedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter
            .SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter
                .SPRING_SECURITY_FORM_USERNAME_KEY,username);
        redirectAttributes.addFlashAttribute("bad_credentials",true);

//        redirectAttributes.addFlashAttribute("username", username);
        return "redirect:/users/login";

    }

    @PostMapping("/logout")
    public ModelAndView logout(){

        this.userService.logout();
        return new ModelAndView("redirect:/");
    }

}
