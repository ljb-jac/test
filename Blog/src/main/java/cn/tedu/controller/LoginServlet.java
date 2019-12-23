package cn.tedu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post请求避免中文乱码
		request.setCharacterEncoding("UTF-8");
		//获取参数
		String userName=request.getParameter("name");
		String password=request.getParameter("pwd");
		//获取是否记住用户名和密码
		String rem=request.getParameter("rem");// rem的值：on/null
		//创建Dao
		UserDao dao=new UserDao();
		User user=dao.login(userName,password);
		if(user==null) {//登录失败重定向到登录页面
			response.sendRedirect(request.getContextPath()+"/ShowLoginServlet");
		}else {
			
			if(rem!=null) {//记住用户名和密码
				Cookie c1=new Cookie("userName",userName);
				Cookie c2=new Cookie("password",password);
				response.addCookie(c1);
				response.addCookie(c2);
			} 
			
			//通过Session实现自动登录
			//获取Session对象
			HttpSession session =request.getSession();
			System.out.println(session.getId());
			//把登录成功得到的用户对象保存在Session中
			session.setAttribute("user", user);
			
			
			
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
		}
		
		
		
	}

}
