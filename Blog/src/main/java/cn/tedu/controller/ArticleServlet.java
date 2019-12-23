package cn.tedu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.Context;

import cn.tedu.dao.ArticleDao;
import cn.tedu.entity.Article;
import cn.tedu.utils.ThUtils;

public class ArticleServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String oId=request.getParameter("oId");
		System.out.println("文章oId:"+oId);
		//通过oId从数据库中查询文章详情
		ArticleDao dao=new ArticleDao();
		//通过文章id查询文章详情信息
		Article a=dao.findById(oId);
		//把数据装进容器并传递给页面
		Context context=new Context();
		context.setVariable("a",a);
		
		ThUtils.write("article", context, response);
		
	}
	


}
