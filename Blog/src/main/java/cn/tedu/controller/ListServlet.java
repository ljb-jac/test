package cn.tedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.Context;

import cn.tedu.dao.ArticleDao;
import cn.tedu.dao.TagDao;
import cn.tedu.entity.Article;
import cn.tedu.entity.Tag;
import cn.tedu.utils.ThUtils;

public class ListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Context context=new Context();

		//创建ArticleDao 查找所有文章
		ArticleDao dao=new ArticleDao();
		List<Article> list=dao.findAll();
		//传递到页面中显示
		context.setVariable("list", list);



		ThUtils.write("list", context, response);



	}



}
