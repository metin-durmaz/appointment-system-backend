package com.turksat.appointmentsystem.controller;

import com.turksat.appointmentsystem.config.helper.ResponseMessage;
import com.turksat.appointmentsystem.model.dto.*;
import com.turksat.appointmentsystem.service.implementations.UserServiceImpl;
import com.turksat.appointmentsystem.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.UserPath.CTRL)
@RestController
@Api(value = "User API")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final ResponseMessage responseMessage;

    public UserController(UserServiceImpl userServiceImpl, ResponseMessage responseMessage) {
        this.userServiceImpl = userServiceImpl;
        this.responseMessage = responseMessage;
    }

    // CREATE
    @PostMapping
    @ApiOperation(value = "Create Operation", response = UserDto.class)
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto){
        return ResponseEntity.ok(this.userServiceImpl.save(userDto));
    }

    // READ
    @GetMapping("/{id}")
    @ApiOperation(value = "Read Operation", response = UserDto.class)
    public ResponseEntity<UserDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.userServiceImpl.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = UserDto.class)
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto userDto){
        return ResponseEntity.ok(this.userServiceImpl.update(id, userDto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(this.userServiceImpl.deleteUser(id));
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "Get One User Operation", response = UserDto.class)
    public ResponseEntity<UserDto> getOne(@PathVariable String username) {
        log.warn("GetOne(User icin) metodu basariyla calisti");
        return ResponseEntity.ok(userServiceImpl.getByUsername(username));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiOperation(value = "Get All Users Operation", response = UserDto.class)
    public ResponseEntity<List<UserDto>> listUsers() {
        log.warn("listUsers(Admin icin) metodu basariyla calisti");
        List<UserDto> data = userServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }

    @RequestMapping(value = "/profile/update/{username}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update Profile Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> updateProfile(@RequestBody ProfileDto profileDto, @PathVariable String username) {
        log.warn("update profile controllerına girdi");
        responseMessage.setResponseMessage(userServiceImpl.updateProfile(profileDto, username));
        return ResponseEntity.ok(responseMessage);
    }

    @RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "Get Profile Operation", response = ProfileDto.class)
    public ResponseEntity<ProfileDto> getProfile(@PathVariable String username) {
        log.warn("get profile(User icin) metodu basariyla calisti");
        return ResponseEntity.ok(userServiceImpl.getProfile(username));
    }

    @RequestMapping(value = "/profile/changepassword/{username}", method = RequestMethod.PUT)
    @ApiOperation(value = "Change Password Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> changePassword(@RequestBody PasswordChangeDto passwordChangeDto, @PathVariable String username) {
        log.warn("Update password");
        responseMessage.setResponseMessage(userServiceImpl.changePassword(passwordChangeDto, username));

        return ResponseEntity.ok(responseMessage);
    }

    @RequestMapping(value = "/profile/email/{username}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update Email Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> updateEmail(@RequestBody UpdateProfileDto updateProfileDto, @PathVariable String username) {
        responseMessage.setResponseMessage(userServiceImpl.updateProfileWithEmail(updateProfileDto, username));
        return ResponseEntity.ok(responseMessage);
    }

}
