package cn.tedu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.context.Context;

import cn.tedu.utils.ThUtils;

public class LogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//删除Session里面的user对象
		HttpSession session=request.getSession();
		session.removeAttribute("user");
		
		response.sendRedirect(request.getContextPath()+"/ShowLoginServlet");
	}


}
