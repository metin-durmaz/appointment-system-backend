package com.turksat.appointmentsystem.controller;

import com.turksat.appointmentsystem.model.dto.*;
import com.turksat.appointmentsystem.config.security.*;
import com.turksat.appointmentsystem.model.entity.User;
import com.turksat.appointmentsystem.model.repository.UserRepository;
import com.turksat.appointmentsystem.service.implementations.UserServiceImpl;
import com.turksat.appointmentsystem.config.helper.RegistrationHelper;
import com.turksat.appointmentsystem.config.helper.ResponseMessage;
import com.turksat.appointmentsystem.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/token") //Token pathine gelen bütün isteklere izin verilecek.Bunun ayarı SecurityConfig'de
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@Api(value = "Account API")
public class AccountController {

    @Autowired
    ResponseMessage responseMessage;

    @Autowired
    RegistrationHelper registrationHelper;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public AccountController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserRepository userRepository, UserServiceImpl userService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;

    }

    @RequestMapping(method = RequestMethod.POST)
    //LoginRequest'i DTO kısmında olusturduk.Front-end'den gelen login objesi gibi düsün
    @ResponseBody
    @ApiOperation(value = "Login Operation", response = TokenResponse.class)
    public ResponseEntity<TokenResponse> login(@Validated @RequestBody LoginRequest loginRequest) throws AuthenticationException {
        log.warn("login'e girdi");
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername().toLowerCase(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);


        User userFromDB = userRepository.findByUsername(loginRequest.getUsername().toLowerCase());

        //Burada sorun olabilir:Bunu yaz:         return ResponseEntity.ok(new AuthToken(token));
        return ResponseEntity.ok(new TokenResponse(userFromDB.getUsername(), userFromDB.getFirstName(), userFromDB.getSurname(), userFromDB.getRole().getName(), token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "Register Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> register(@Validated @RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {

        System.out.println("kayda geldi" + registrationRequest.getEmail());

        responseMessage.setResponseMessage(registrationHelper.registrationValidator(registrationRequest));

        if (responseMessage.getResponseMessage() != null) {
            responseMessage.setResponseType(0);
            return ResponseEntity.ok(responseMessage);
        }

        Boolean response = userService.register(registrationRequest);

        if (!response) {
            responseMessage.setResponseMessage("Kayıt sırasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        responseMessage.setResponseType(1);
        responseMessage.setResponseMessage("Basariyla kaydoldunuz!");

        return ResponseEntity.ok(responseMessage);

    }

}

