package cn.tedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.context.Context;

import cn.tedu.dao.ArticleDao;
import cn.tedu.dao.LinkDao;
import cn.tedu.dao.TagDao;
import cn.tedu.entity.Article;
import cn.tedu.entity.Link;
import cn.tedu.entity.Tag;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;

public class HomeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//判断是否登录过 如果没有则显示登录页面
		HttpSession session =request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {
			response.sendRedirect(request.getContextPath()+"/ShowLoginServlet");
			return; //后面代码不执行
		}
		
		
		Context context=new Context();
		//将当前登录的用户名添加到容器中
		context.setVariable("userName",user.getUserName());//在index中th用户名
		
		ArticleDao dao=new ArticleDao();

		//查询首页置顶8篇文章
		List<Article> list=dao.getHomeList();
		//把第一篇文章传递到页面中显示
		context.setVariable("first", list.get(0));

		//把第2，3，4篇文章传递到页面
		List<Article> second=list.subList(1, 4);
		context.setVariable("second", second);

		//第5-8篇文章
		context.setVariable("third", list.subList(4, list.size()));

		//查询最新文章列表
		List<Article> newList=dao.getNewList();
		//把最新文章列表传递到页面
		context.setVariable("newList", newList);

		//查询评论最热列表
		List<Article> commentList=dao.getCommentList();
		context.setVariable("commentList", commentList);

		//查询浏览最多列表
		List<Article> viewList=dao.getViewList();
		context.setVariable("viewList", viewList);
		
		//查询使用最多的标签
		TagDao tagDao=new TagDao();
		List<Tag> tags=tagDao.getList();
		//把标签传递到页面中
		context.setVariable("tags", tags);
		
		//实现友情链接步骤：
		//创建Link实体(title,address)
		//创建LinkDao 提供findAll方法返回集合
		//在当前位置创建dao 并获取集合
		//把集合传递到页面中，在页面中控制显示
		LinkDao ld=new LinkDao();
		List<Link> links=ld.findAll();
//		System.out.println("友情链接数量"+links.size());
		context.setVariable("links",links);
		
		ThUtils.write("index", context, response);


	}



}
