package com.app.prayer.serviceImp;

import com.app.prayer.dto.UserDto;
import com.app.prayer.entity.Role;
import com.app.prayer.entity.User;
import com.app.prayer.exception.ResourceNotFoundException;
import com.app.prayer.repository.RoleRepository;
import com.app.prayer.repository.UserRepository;
import com.app.prayer.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void initRoleAndUser() {

//        Role adminRole = new Role();
//        adminRole.setRoleId(1);
//        adminRole.setRoleName("Admin");
//        adminRole.setRoleDescription("Admin role");
//        roleRepository.save(adminRole);
//
//        Role userRole = new Role();
//        userRole.setRoleId(2);
//        userRole.setRoleName("User");
//        userRole.setRoleDescription("Default role for newly created record");
//        roleRepository.save(userRole);
//
//        User adminUser = new User();
//        adminUser.setEmail("admin@gmail.com");
//        adminUser.setPassword(getEncodedPassword("admin@123"));
//        adminUser.setFirst_name("admin");
//        adminUser.setLast_name("test");
//        adminUser.setCountry("india");
//        adminUser.setState("maharashtra");
//        adminUser.setCity("pune");
//        Set<Role> adminRoles = new HashSet<>();
//        adminRoles.add(adminRole);
//        adminUser.setRoles(adminRoles);
//        userRepository.save(adminUser);
//
//
//        User user = new User();
//        user.setEmail("vicky@gmail.com");
//        user.setPassword(getEncodedPassword("vicky@123"));
//        user.setFirst_name("vicky");
//        user.setLast_name("test");
//        user.setCountry("india");
//        user.setState("maharastra");
//        user.setCity("pune");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRoles(userRoles);
//        userRepository.save(user);
	}

	@Override
	public User registerNewUser(User user) {
		// Role role = roleRepository.findById("User").get();
		Role role = roleRepository.findByRoleName("user");

		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);
		// user.setRole(userRoles);
		user.setRoles(userRoles);
		user.setPassword(getEncodedPassword(user.getPassword()));

		return userRepository.save(user);
	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		// User user = new User();
		Role role = roleRepository.findByRoleName("user");
		Set<Role> userRoles = new HashSet<>();
		userRoles.add(role);
		// user.setRole(userRoles);
		User user = this.dtoToUser(userDto);
		user.setRoles(userRoles);
		user.setPassword(getEncodedPassword(user.getPassword()));

		User savedUser = this.userRepository.save(user);
		return this.usertoDto(savedUser);
	}

	@Override
	public UserDto getUserByid(int userid) {
		User user = this.userRepository.findById(userid)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userid));

		return this.usertoDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.usertoDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	public User dtoToUser(UserDto userDto) {
//		User user = new User();
//		user.setUserid(userDto.getUserid());
//		user.setEmail(userDto.getEmail());
//		user.setFirst_name(userDto.getFirst_name());
//		user.setLast_name(userDto.getLast_name());
//		user.setCountry(userDto.getCountry());
//		user.setCity(userDto.getCity());
//		user.setState(userDto.getState());
//		user.setPassword(userDto.getPassword());

		User user = this.modelMapper.map(userDto, User.class);

		return user;
	}

	public UserDto usertoDto(User user) {
//		UserDto userDto = new UserDto();
//		userDto.setUserid(user.getUserid());
//		userDto.setEmail(user.getEmail());
//		userDto.setFirst_name(user.getFirst_name());
//		userDto.setLast_name(user.getLast_name());
//		userDto.setCountry(user.getCountry());
//		userDto.setCity(user.getCity());
//		userDto.setState(user.getState());
//		userDto.setPassword(user.getPassword());
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
