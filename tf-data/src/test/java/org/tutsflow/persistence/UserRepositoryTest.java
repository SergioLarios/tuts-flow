package org.tutsflow.persistence;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.model.User;
import org.tutsflow.parent.DatabaseConectionTest;
import org.tutsflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class UserRepositoryTest extends DatabaseConectionTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserLocalService userLocalService;
	
	@Test
	public void userTests() {
		
//		String testString= String.valueOf(new Date().getTime());
//		
//		User user = new User();
//		user.setUserName("testName-"+testString);
//		user.setMail(testString +"@gmail.com");
//		user.setUserId(Long.valueOf(testString));
//		
//		user  = userRepository.save(user);
//		
//		assertNotEquals(0, user.getUserId());
//		
//		long userId = user.getUserId();
//		user = null;
//		user = userRepository.findOne(userId);
//		
//		assertNotNull(user);
//		
//		userRepository.delete(user);
//		
//		Page<User> users = userRepository.findAll(new PageRequest(0, 5));
//		
//		for (User user2 : users.getContent()) {
//			System.out.println(user2.toJSONString());
//		}
		
		int page = 0;
		int size = 5;
		
		// User list
		Page<User> usersP = userLocalService.findAll(new PageRequest(page, size));
		List<User> users = usersP.getContent();

		// Pages Text
		int rowsTo = 0;
		if (page == 0 ) { rowsTo = size; }
		else {rowsTo = size * page; }
		int rowsFrom = (rowsTo - size) + 1;
		
		// View creation
		PaginationJsonView<User> output = new PaginationJsonView<>();
		output.setData(users);
		output.setTotalEntries((int)usersP.getTotalElements());
		output.setTotalPages(usersP.getTotalPages());
		output.setPageText("Rows 1 to 5 | 3");
		
		System.out.println(output.toJSONString());
		
	}
	
}
