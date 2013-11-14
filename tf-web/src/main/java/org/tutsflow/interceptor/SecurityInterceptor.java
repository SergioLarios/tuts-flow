package org.tutsflow.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.tutsflow.constant.Constants;
import org.tutsflow.constant.Mappings;
import org.tutsflow.constant.PermissionConstants;
import org.tutsflow.constant.StringPool;
import org.tutsflow.constant.UserConstants;
import org.tutsflow.local.service.PermissionLocalService;
import org.tutsflow.model.Permission;
import org.tutsflow.model.User;
import org.tutsflow.util.Validator;
import org.tutsflow.web.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecurityInterceptor implements HandlerInterceptor {

	/* *******************************************************
	 * ********************** Pre Handle *********************
	 * *******************************************************/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestUri = request.getRequestURI();
		
		// We don't want to check the resources
		if (!requestUri.startsWith(Mappings.RESURCE)) {
			
			List<Permission> mappingPermissions = permissionLocalService.findByType(PermissionConstants.TYPE_MAPPING);
			
			for (Permission permission : mappingPermissions) {

				// If the patter matches and it's not public we'll have to check the security
				if (checkPattern(requestUri, permission) && !permission.isPublic()) {
					
					HttpSession session = request.getSession();
					
					User user = (User) session.getAttribute(Constants.SESSION_USER);
					
					if (Validator.isNotNull(user) && !(user.getType() == UserConstants.ADMIN)) {
						
						List<Long> usersAllowed = permission.getUserIdList();
						
						// Check if the user is allowed
						for (Long userId : usersAllowed) {
							if (user.getUserId() == userId) { return true; }
						}
						
						SpringUtils.sendError(response);
						return false;
					}
					else if (Validator.isNull(user)) {
						SpringUtils.sendError(response);
						return false;
					}

				}
			
			}
			
		}
		
		return true;
	}

	/* *******************************************************
	 * ********************* Post Handle *********************
	 * *******************************************************/
	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
		
	}
	
	/* *******************************************************
	 * ******************* After Completion ******************
	 * *******************************************************/
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
		
	}
	
	/* *******************************
	 ******* Private Methods *********
	 ****************************** */
	
	private boolean checkPattern(String requestUri, Permission permission) {
		
		String pattern = StringPool.CARET + permission.getAction();
		pattern = pattern.replace(StringPool.STAR, StringPool.PERIOD + StringPool.STAR);
		if (!pattern.endsWith(StringPool.STAR)) { pattern = pattern + StringPool.STAR; }
		
		return requestUri.matches(pattern);
	}
	
	/* *******************************
	 ******** Repositories ***********
	 ****************************** */
	
	@Autowired
	private PermissionLocalService permissionLocalService;

	/* *******************************
	 ****** Getters and Setters ******
	 ****************************** */

	public PermissionLocalService getPermissionLocalService() { return permissionLocalService; }
	public void setPermissionLocalService( PermissionLocalService permissionLocalService) {
		this.permissionLocalService = permissionLocalService;
	}
	
}
