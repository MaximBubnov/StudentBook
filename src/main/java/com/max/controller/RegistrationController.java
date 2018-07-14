package com.max.controller;

import com.max.domain.User;
import com.max.domain.dto.CaptchaResponseDto;
import com.max.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    private static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
            ) {

        //наш урл по которому нужно будет перейти
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        //и передаем ему параметры
        CaptchaResponseDto responseDto = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        //если не успешно все прошло, то говорим проверить каптчу
        if(!responseDto.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }

        //если пассворд2 (для проверки) не пустой
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);

        //если пассворд2 (для проверки) не пустой
        if(!isConfirmEmpty) {
            model.addAttribute("password2", "Password confirmation cannot be empty");
        }

        //сходяться ли два пароля которые ввел пользователь при регистрации
        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        //есть ли у нас ошибки валидации
        if(isConfirmEmpty || bindingResult.hasErrors() || !responseDto.isSuccess()) {
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

    //подтверждение регистрации
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code, User user) {
        boolean isActive = userService.activateUser(code);

        if(isActive) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");

        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found");
        }

        return "login";
    }
}
