package www.spring.security.common.module;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckInterecptor extends HandlerInterceptorAdapter {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("@@@@@@@@@@@@@CheckInterecptor preHandle() 메서드 실행@@@@@@@@@@@@@");
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception {
		logger.info("@@@@@@@@@@@@@CheckInterecptor postHandle() 메서드 실행@@@@@@@@@@@@@");
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("@@@@@@@@@@@@@CheckInterecptor afterCompletion() 메서드 실행@@@@@@@@@@@@@");
		
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterConcurrentHandlingStarted(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("@@@@@@@@@@@@@CheckInterecptor afterConcurrentHandlingStarted() 메서드 실행@@@@@@@@@@@@@");
	}
	
}
