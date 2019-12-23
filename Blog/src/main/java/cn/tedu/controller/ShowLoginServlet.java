package cn.tedu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.context.Context;

import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;

public class ShowLoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//判断Session中是否有user对象 有则说明登录成功过 直接显示首页
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user"); //Session里的对象是object类 需强制
		if(user!=null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;//后面代码不执行
		}
		
		
		Context context=new Context();
		
		//取出cookie中的用户名和密码
		Cookie[] cookies=request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("userName")) {
				//把用户名添加到容器中
				context.setVariable("userName", cookie.getValue());				
			}
			//判断保存的是否是密码
			if(cookie.getName().equals("password")) {
				//把密码添加到容器中
				context.setVariable("password", cookie.getValue());	
			}
		}
		
		ThUtils.write("login", context, response);
	}

	

}
