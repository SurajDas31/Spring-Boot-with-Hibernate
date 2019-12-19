package com.virus.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.virus.model.User;
import com.virus.service.UserService;

@RestController
public class FirstController {
	private UserService service;

	@Autowired
	public FirstController(UserService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> findAll() {
		return service.findAll();
	}

	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		User user = service.findById(userId);
		if (user == null) {
			throw new RuntimeException("User id not found - " + userId);
		}
		return user;
	}

	@PostMapping("/user")
	public User addUser(@RequestBody User user) {
		user.setId(0);;
		service.save(user);
		return user;
	}

	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		service.save(user);
		return user;
	}

	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable int userId) {
		User user = service.findById(userId);
		if (user == null) {
			throw new RuntimeException("User id not found - " + userId);
		}
		service.deleteById(userId);
		return "Deleted user id " + userId;
	}
}
