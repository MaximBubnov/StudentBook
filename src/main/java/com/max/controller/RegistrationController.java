package com.max.controller;

import com.max.domain.User;
import com.max.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    private static final String CAPTCH_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService userService;

    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            //@RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
            ) {

        //если пассворд2 (для проверки) не пустой
        boolean isConfirmEpty = StringUtils.isEmpty(passwordConfirm);

        //если пассворд2 (для проверки) не пустой
        if(!isConfirmEpty) {
            model.addAttribute("password2", "Password confirmation cannot be empty");
        }

        //сходяться ли два пароля которые ввел пользователь при регистрации
        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        //есть ли у нас ошибки валидации
        if(isConfirmEpty || bindingResult.hasErrors() /*|| !responseDto.isSuccess()*/) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        //идем в сервси там есть метод по добавления пользователя - ретернет тру или фалс
        //если не смоги добавить пользователя
        if(!userService.addUser(user)) {
            model.addAttribute("usernameError", "User Exists!");
            return "registration";
        }

        return "redirect:/main";
    }
}
