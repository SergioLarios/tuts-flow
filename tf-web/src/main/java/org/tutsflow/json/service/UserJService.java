package org.tutsflow.json.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tutsflow.constant.StringPool;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.model.User;

import static org.tutsflow.constant.Services.*;

@Controller
public class UserJService {
	
	/* *******************************************************
	 * *** USER find all : /service/user/{page}/{size} ***** 
	 * *******************************************************/

	@RequestMapping(value = USER_FIND_ALL, method = RequestMethod.GET, produces = {TXT_P, APP_J})
	@ResponseBody
	public String findAllPageable(@PathVariable int page, @PathVariable int size,
			HttpServletRequest request, HttpServletResponse response) {
		
		String output = StringPool.OPEN_BRACKET;
		
		Page<User> usersP = userLocalService.findAll(new PageRequest(page, size));
		List<User> users = usersP.getContent();

		for (User user : users) {
			user.setPassword("");
			output = output + user.toJSONString() + StringPool.COMMA;
		}
		
		output = output.substring(0, output.length() - 1) + StringPool.CLOSE_BRACKET;
		
		return output;
	}
	
	/* *******************************
	 ******* Injected objects ********
	 ****************************** */
	
	private UserLocalService userLocalService;

	/* *******************************
	 ********** Constructor **********
	 ******************************* */
	
	@Autowired
	public UserJService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}
	
}
