package cn.tedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建Cookie对象并保存数据
		Cookie c1=new Cookie("msg1", "HelloWorld");
		//如果需要保存中文需要进行url编码
		Cookie c2=new Cookie("msg2",URLEncoder.encode("大家好", "UTF-8"));
		//设置时间 如果不设置 数据只保存在浏览器内存中，关闭浏览器则清除
		//设置时间后 数据会保存在磁盘中，到指定时间后才删除
		c1.setMaxAge(60*60*24*30);//保存一个月
		//把cookie下发到浏览器
		response.addCookie(c1);
		response.addCookie(c2);
		//显示添加完成
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.print("添加完成！");
		pw.close();
	}

}
