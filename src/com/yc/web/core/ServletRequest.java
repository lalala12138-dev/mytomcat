package com.yc.web.core;

/**
 * 包装http请求的处理
 * @author lalala
 *
 */
public interface ServletRequest {
	/**
	 * 解析请求的方法
	 */
	public void parse();
	
	/**
	 * 获取请求参数的方法
	 * @param key 键
	 * @return
	 */
	public String getParamter(String key);
	
	/**
	 * 获取解析出来的请求地址
	 * @return
	 */
	public String getUrl();
	
	/**
	 * 获取请求方式
	 * @return
	 */
	public String getMethod();
	
	/**
	 * 获取session
	 * @return
	 */
	public HttpSession getSession();
	
	/**
	 * 获取Cookie
	 * @return
	 */
	public Cookie[] getCookies();
	
	/**
	 * 检车判断是否有JSessionId
	 * @return
	 */
	public boolean checkJSessionId();
	
	/**
	 * 获取用户的JSessionId
	 * @return
	 */
	public String getJSessionId();
}
