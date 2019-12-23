package cn.tedu.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException {
		req.setCharacterEncoding("UTE-8");
		System.out.println("…Ë÷√±‡¬Î");
		chain.doFilter(req, res);
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
