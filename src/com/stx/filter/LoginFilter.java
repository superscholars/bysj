package com.stx.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.stx.entity.Constants;

/**
 * 登陆过滤，如果session失效则需要重新登陆
 * @author gzzdsg
 * 2016年3月10日
 */
public class LoginFilter implements javax.servlet.Filter{

	private String[] params ;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		params = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		String url = request.getServletPath();
		System.out.println(url);
		if(url.equals(Constants.INDEX_PAGE)){
			chain.doFilter(req, res);
			return;
		}
		for(String param : params){
			if(param.equals(url)){
				chain.doFilter(req, res);
				return;
			}
		}
		HttpSession session = request.getSession();
		if(session == null){
			switch(url.substring(0,5)){
			case "/user":
				request.getRequestDispatcher("/user/user_login.action").forward(req, res);
				break;
			case "/admi":
				request.getRequestDispatcher("/admin/admin_login.action").forward(req, res);
				break;
			case "/merc":
				request.getRequestDispatcher("/merchant/merchant_login.action").forward(req, res);
				break;
			}
		}
		Object obj = session.getAttribute(Constants.USER);
		if(obj==null){
			switch(url.substring(0,5)){
			case "/user":
				request.getRequestDispatcher("/user/user_login.action").forward(req, res);
				break;
			case "/admi":
				request.getRequestDispatcher("/admin/admin_login.action").forward(req, res);
				break;
			case "/merc":
				request.getRequestDispatcher("/merchant/merchant_login.action").forward(req, res);
				break;
			}
		}
		chain.doFilter(req, res);
		return;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		params = config.getInitParameter("disAuth").split(";");
	}

}
