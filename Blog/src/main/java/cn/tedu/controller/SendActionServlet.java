package cn.tedu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.ArticleDao;
import cn.tedu.entity.Article;
import cn.tedu.entity.User;

public class SendActionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String title=request.getParameter("title");
		String abs=request.getParameter("abs");
		String content=request.getParameter("content");
		String imgName=request.getParameter("imgName");
		String putTop=request.getParameter("putTop");
		int isTop = putTop==null?0:1;
		//把数据封装到对象中
		Article a =new Article(0, title, abs, 0, 0, isTop, 0, 0, imgName, null);
		a.setContent(content);//正文和文章对象关联
		ArticleDao dao=new ArticleDao();
		
		//取出Session中的user对象
		User user=(User) request.getSession().getAttribute("user");		
		
		dao.save(a,user);
		//重定向到列表页面
		response.sendRedirect(request.getContextPath()+"/ListServlet");
	}



}
