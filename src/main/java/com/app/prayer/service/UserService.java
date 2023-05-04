package com.app.prayer.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.app.prayer.dto.UserDto;
import com.app.prayer.entity.User;

public interface UserService {

	public void initRoleAndUser();

	public User registerNewUser(User user);

	UserDto createUser(UserDto user);

	UserDto getUserByid(int userid);

	List<UserDto> getAllUsers();

}
