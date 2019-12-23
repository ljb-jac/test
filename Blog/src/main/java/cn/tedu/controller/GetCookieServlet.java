package cn.tedu.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCookieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取cookie
		Cookie[] cookies=request.getCookies();
		for (Cookie cookie : cookies) {
			String name=cookie.getName();
			String value=cookie.getValue();
			//对value进行URL解码
			value=URLDecoder.decode(value, "UTF-8");
			System.out.println(name+":"+value);

			
		}
	}

}
