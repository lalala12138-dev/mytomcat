package com.yc.web.core;

import java.io.PrintWriter;

public interface ServletResponse {
	/**
	 * 输出响应信息的方法
	 * @return
	 */
	public PrintWriter getWriter();
	
	/**
	 * 重定向方法
	 * @param url 要定位的地址
	 */
	public void sendRedirect(String url);
}
