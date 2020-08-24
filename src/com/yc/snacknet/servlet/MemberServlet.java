package com.yc.snacknet.servlet;

import com.yc.web.core.HttpServlet;
import com.yc.web.core.ServletRequest;
import com.yc.web.core.ServletResponse;

public class MemberServlet extends HttpServlet{
	@Override
	public void doGet(ServletRequest request, ServletResponse response) {
		doPost(request,response);
	}

	@Override
	public void doPost(ServletRequest request, ServletResponse response) {
		System.out.println("pwd="+request.getParamter("pwd"));
		System.out.println("account="+request.getParamter("account"));
		
		response.sendRedirect("/snacknet/index.html");
	}
}
