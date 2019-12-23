package cn.tedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.Context;

import cn.tedu.dao.ArticleDao;
import cn.tedu.entity.Article;
import cn.tedu.utils.ThUtils;

public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String keyword=request.getParameter("keyword");
		System.out.println(keyword);
		//创建Dao查询相关文章
		ArticleDao dao=new ArticleDao();
		List<Article> list=dao.findByKeyword(keyword);
		//把查询到的集合传递到页面中
		Context context=new Context();
		context.setVariable("list", list);//重用list页面 "list"不能变
		
		ThUtils.write("list", context, response);
		
		
	}



}
