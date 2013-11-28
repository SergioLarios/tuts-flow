package org.tutsflow.json.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.model.User;
import org.tutsflow.view.PaginationJsonView;

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
		
		// User list
		Page<User> usersP = userLocalService.findAll(new PageRequest(page, size));
		List<User> users = usersP.getContent();
		int totalElements = (int)usersP.getTotalElements();
		
		// Pages Text
		int rowsTo = size * (page + 1);
		int rowsFrom = (rowsTo - size) + 1;
		if (rowsTo > totalElements) {rowsTo = totalElements;}
		
		// View creation
		PaginationJsonView<User> output = new PaginationJsonView<>();
		output.setData(users);
		output.setTotalEntries(totalElements);
		output.setTotalPages(usersP.getTotalPages());
		output.setPageText( messageSource.getMessage(
			"cp.pages.text", 
			new Object[]{rowsFrom, rowsTo, output.getTotalEntries()}, 
			localeResolver.resolveLocale(request)));
		
		return output.toJSONString();
	}
	
	/* *******************************
	 ******* Injected objects ********
	 ****************************** */
	
	private UserLocalService userLocalService;
	private ReloadableResourceBundleMessageSource messageSource;
	private SessionLocaleResolver localeResolver;
	
	/* *******************************
	 ********** Constructor **********
	 ******************************* */
	
	@Autowired
	public UserJService(UserLocalService userLocalService,
			ReloadableResourceBundleMessageSource messageSource,
			SessionLocaleResolver localeResolver) {
		this.userLocalService = userLocalService;
		this.messageSource = messageSource;
		this.localeResolver = localeResolver;
	}
	
}
