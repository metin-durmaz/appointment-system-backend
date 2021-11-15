package com.turksat.appointmentsystem.service;

import com.turksat.appointmentsystem.model.dto.*;
import com.turksat.appointmentsystem.model.entity.User;
import java.util.List;

public interface UserService {

    UserDto getByUsername(String username);

    UserDto save(UserDto user);

    List<UserDto> getAll();

    Boolean isUsernameExists(String username);

    Boolean isEmailExists(String email);

    Boolean deleteUser(Long id);

    Boolean register(RegistrationRequest registrationRequest);

    String updateProfile(ProfileDto profile, String username);

    ProfileDto getProfile(String username);

    void createProfile(User user);

    String changePassword(PasswordChangeDto passwordChangeDto, String username);

    String updateProfileWithEmail(UpdateProfileDto updateProfileDto, String username);

	String emailCheck(String email);
	
	UserDto getById(Long id);

	UserDto update(Long id , UserDto userDto);
}