package cn.tedu.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException {
		
		Thread t=Thread.currentThread();
		System.out.println(t);
		
		System.out.println("Hello");
		//chain.doFilter(); //处理后续链条
		chain.doFilter(req, res);
		System.out.println("World");
	}

	

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}


}
