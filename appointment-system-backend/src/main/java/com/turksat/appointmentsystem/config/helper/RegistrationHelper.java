package com.turksat.appointmentsystem.config.helper;

import com.turksat.appointmentsystem.model.dto.RegistrationRequest;
import com.turksat.appointmentsystem.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class RegistrationHelper {
    private final UserService userService;

    public RegistrationHelper(UserService userService) {
        this.userService = userService;
    }

    public String registrationValidator(RegistrationRequest registrationRequest){

        if(userService.isUsernameExists(registrationRequest.getUsername())){
            return "Kullanıcı adı zaten kullanılmakta.";
        }

        if(userService.isEmailExists(registrationRequest.getEmail())){
            return "Email zaten kullanılmakta.";
        }

        if(!((userService.emailCheck(registrationRequest.getEmail())).equals("true"))){
            return "Lütfen geçerli bir email adresi girin";
        }

        return null;


    }




}
