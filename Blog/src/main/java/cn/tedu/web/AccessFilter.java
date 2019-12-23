package cn.tedu.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.entity.User;

public class AccessFilter implements Filter{

	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException {
		//如果登陆就显示信息，没有登陆就重定向
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		//如果用户没有登陆，则user值为null
		if(user==null) {
			//重定向到登录页面
			System.out.println("重定向到登录页面");
			String path=request.getContextPath()+"/ShowLoginServlet";
			response.sendRedirect(path);
		}else {
			//放过请求，进行处理请求链条
			chain.doFilter(request, response);
			
		}
				
		
	}
	
	
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	
	public void destroy() {
		
	}

}
