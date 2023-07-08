package Enrollment.Enrollment.controller;

import Enrollment.Enrollment.dto.RegistrationDto;
import Enrollment.Enrollment.models.UserEntity;
import Enrollment.Enrollment.security.SecurityUtil;
import Enrollment.Enrollment.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String createRegisterForm(Model model){
        UserEntity user = new UserEntity();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("user")RegistrationDto registrationDto, BindingResult result,Model model){
        UserEntity existingEmail = userService.findByEmail(registrationDto.getEmail());
        if (existingEmail != null && existingEmail.getEmail() != null && !existingEmail.getEmail().isEmpty()){

            return "redirect:/register?fail";
        }

        UserEntity existingUsername = userService.findByUsername(registrationDto.getUsername());
        if (existingUsername != null && existingUsername.getUsername() != null && !existingUsername.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }

        if (result.hasErrors()){
            model.addAttribute("users",registrationDto);
            return "register";
        }
        userService.saveUser(registrationDto);
        return "redirect:/login?success";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if(username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        model.addAttribute("user",user);
        return "login";
    }


}
