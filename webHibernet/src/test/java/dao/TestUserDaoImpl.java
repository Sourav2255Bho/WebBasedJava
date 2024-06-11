package dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pojos.User;

class TestUserDaoImpl {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testUserLogin() {
		UserDaoImpl dao = new UserDaoImpl();
		User user = dao.userLogin("tanvi@04", "TanviSourav");
		System.out.println(user);
		assertEquals("Tanvi", user.getName());
	}

}
