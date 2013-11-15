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

public class PageInterceptor implements HandlerInterceptor {

	/* *******************************************************
	 * ********************** Pre Handle *********************
	 * *******************************************************/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		String conPath = request.getContextPath();
		String uri = request.getRequestURI();
		
		if (conPath != StringPool.SLASH) {
			uri = uri.replace(conPath, StringPool.BLANK);
		}
		
		Page page = new Page();
		if (uri.equals(Mappings.HOME)){
			page.setType(Page.HOME);
		}
		else if (uri.startsWith(Mappings.EXPLORE)) {
			page.setType(Page.EXPLORE);
		}
		else if (uri.startsWith(Mappings.HOW_TO)) {
			page.setType(Page.HOW_TO);
		}
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
