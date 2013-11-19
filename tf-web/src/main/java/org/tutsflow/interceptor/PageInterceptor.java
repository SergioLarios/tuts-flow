package org.tutsflow.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.tutsflow.constant.Constants;
import org.tutsflow.constant.Mappings;
import org.tutsflow.constant.StringPool;
import org.tutsflow.session.Page;
import org.tutsflow.web.UrlUtils;

public class PageInterceptor implements HandlerInterceptor {

	/* *******************************************************
	 * ********************** Pre Handle *********************
	 * *******************************************************/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		String uri = UrlUtils.getUri(request);
		
		Page page = new Page();
		
		// Home
		if (uri.equals(Mappings.HOME)){
			page.setType(Page.HOME);
		}
		
		// Explore
		else if (uri.startsWith(Mappings.EXPLORE + StringPool.SLASH)) {
			page.setType(Page.EXPLORE);
		}
		
		// How to
		else if (uri.startsWith(Mappings.HOW_TO + StringPool.SLASH)) {
			page.setType(Page.HOW_TO);
		}
		
		// Login
		else if (uri.startsWith(Mappings.LOGIN + StringPool.SLASH)) {
			page.setType(Page.LOG_IN);
		}
		
		// Registrer
		else if (uri.startsWith(Mappings.CREATE_ACCOUNT + StringPool.SLASH)) {
			page.setType(Page.REGISTRER);
		}
		
		// Other
		else {
			page.setType(Page.OTHER);
		}
		
		session.setAttribute(Constants.SESSION_PAGE, page);
		
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
	
}
