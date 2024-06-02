package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.entities.User;
import static com.app.utils.DBUtils.*;

public class UserDaoImpl implements UserDao{
	private Connection cn;
	private PreparedStatement pst1, pst2;
	
	

	public UserDaoImpl() throws SQLException {
		super();
		cn = openConnection();
		
		pst1 = cn.prepareStatement("Select * from customer where UserName = ? and Password = ?");
		pst2 = cn.prepareStatement("insert into customer(Name, UserName, Password) values (?, ?, ?)");
		
		System.out.println("UserDao created");
	}

	@Override
	public User signIn(String email, String password) throws SQLException {
		pst1.setString(1, email);
		pst1.setString(2, password);
		
		try(ResultSet rest = pst1.executeQuery()){
			if(rest.next()) {
				return new User(rest.getInt(1), rest.getString(2), rest.getString(3), rest.getString(4));
			}
		}
		return null;
	}

	@Override
	public boolean registration(String name, String email, String Password) throws SQLException {
		pst2.setString(1, name);
		pst2.setString(2, email);
		pst2.setString(3, Password);
		
		int rows = pst2.executeUpdate();
		if(rows == 1) {
			return true;
		}
		return false;
	}
	
	public void cleanUp() throws SQLException {
		System.out.println("UserDao Cleaned Up");
		if(pst1 != null)
			pst1.close();
		if(pst2 != null)
			pst2.close();
		
		closeConnection();
	}

}
