package com.oganalysis.dao;
import java.util.List;

import com.oganalysis.entities.User;
public interface UserDao {	
	public User getUser(String email);
	public boolean updateUser(User user);	 
	public List<User> getPasswords();
}
