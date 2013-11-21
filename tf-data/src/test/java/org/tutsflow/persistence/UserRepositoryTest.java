package org.tutsflow.persistence;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.tutsflow.model.User;
import org.tutsflow.parent.DatabaseConectionTest;
import org.tutsflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class UserRepositoryTest extends DatabaseConectionTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void userTests() {
		
		String testString= String.valueOf(new Date().getTime());
		
		User user = new User();
		user.setUserName("testName-"+testString);
		user.setMail(testString +"@gmail.com");
		user.setUserId(Long.valueOf(testString));
		
		user  = userRepository.save(user);
		
		assertNotEquals(0, user.getUserId());
		
		long userId = user.getUserId();
		user = null;
		user = userRepository.findOne(userId);
		
		assertNotNull(user);
		
		userRepository.delete(user);
		
		Page<User> users = userRepository.findAll(new PageRequest(0, 5));
		
		for (User user2 : users.getContent()) {
			System.out.println(user2.toJSONString());
		}
		
	}
	
}
