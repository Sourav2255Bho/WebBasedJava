package com.app.dao;

import java.sql.SQLException;

import com.app.entities.User;

public interface UserDao {
	User signIn(String email, String password) throws SQLException;
	
	boolean registration(String name , String email, String Password) throws SQLException;
}
