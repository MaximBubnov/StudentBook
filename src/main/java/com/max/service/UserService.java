package com.max.service;

import com.max.domain.Role;
import com.max.domain.User;
import com.max.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        //если уже есть такой пользователь
        if(userFromDb != null) {
            return false;
        }

        //если нет
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        //если у него есть емаил, то отправим ему сообщение

        sendMessage(user);

        return true;
    }


    //отправка сообщения с активационным кодом
    private void sendMessage(User user) {
        if(!StringUtils.isEmpty(user.getEmail())) {

            String message = String.format(
                    "Hello! \n" +
                            "Welcome to Exams Book МАГУ! Your login and password:  \n" +
                            "Login: %s \n" +
                            "Password: %s \n" +
                            "Please, visit next link : http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getPassword(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    //пользователь активирован или нет
    public boolean activateUser(String code) {
        //возвращаем пользователя по определенному коду
        User user = userRepo.findByActivationCode(code);

        if(user == null) {
            return false;
        }
        //если все ок - код = 0
        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if(roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }


    public void updateProfile(User user, String password, String email) {
        //получаем емаил изначальные
        String userEmail = user.getEmail();

        //изменен ли емаил
        boolean isEmailChanged = ((email != null && !email.equals(userEmail)) ||
                (userEmail!=null && !userEmail.equals(email)));

        //если да
        if(isEmailChanged) {
            user.setEmail(email); // то устанавливаем новый
            //если он установил новый емаил
            if(!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString()); // генерируем ему новый код
            }
        }

        //если юзер установил новый пароль
        if(!StringUtils.isEmpty(password)) {
            user.setPassword(password); // устанавливаем его юзеру
        }

        //после всего этого сохраняем пользователя в базе данных
        userRepo.save(user);

        //и отправляем ему активайшен код, только тогда когда емаил был изменен
        if (isEmailChanged) {
            sendMessage(user);
        }
    }
}
