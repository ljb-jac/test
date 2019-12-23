package cn.tedu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

public class RegServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String userName=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("pwd"); //rpwd前端可实现验证，减少服务器压力
		//封装
		User user=new User(0,userName,password,email);
		//通过Dao去保存
		UserDao dao=new UserDao();
		dao.save(user);
		//重定向到登录页面
		response.sendRedirect(request.getContextPath()+"/ShowLoginServlet");
		
	}


}
