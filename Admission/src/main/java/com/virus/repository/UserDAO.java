package com.virus.repository;

import java.util.List;

import com.virus.model.User;

public interface UserDAO{ 
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public void save(User user);
	
	public void deleteById(int id);
}
