package com.yc.web.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.yc.tomcat.core.ParseXml;
import com.yc.tomcat.core.ReadConfig;
import com.yc.tomcat.core.TomcatConstants;

/**
 * 基于http协议的响应
 * @author lalala
 *
 */
public class HttpServletResponse implements ServletResponse{
	private OutputStream os;
	private String basePath=TomcatConstants.BASE_PATH;
	
	public HttpServletResponse(OutputStream os) {
		this.os=os;
	}

	@Override
	public PrintWriter getWriter() {
		return null;
	}

	@Override
	public void sendRedirect(String url) {
		if(url==null || "".equals(url)) {
			error404(url);
			return;
		}
		
		if(url.indexOf("/")==url.lastIndexOf("/") && url.indexOf("/")<url.length()) { // /DayFresh,即指定了项目名
			send302(url);
		}else {
			if(url.endsWith("/")) { //说明没有指定具体的资源   /Snacknet/   /Snacknet/back/
				String defaultPath=ReadConfig.getInstance().getProperty("default");
				
				//读取默认资源
				File fl=new File(basePath,url.substring(1).replace("/", "\\")+defaultPath);
				//System.out.println(fl.getAbsolutePath());
				if(!fl.exists()) {
					error404(url);
					return;
				}
				send200(readFile(fl), defaultPath.substring(defaultPath.lastIndexOf(".")+1).toLowerCase());
			}else {
				File fl=new File(basePath,url.substring(1).replace("/", "\\"));
				//System.out.println(fl.getAbsolutePath());
				if(!fl.exists() || !fl.isFile()) {
					error404(url);
					return;
				}
				send200(readFile(fl), url.substring(url.lastIndexOf(".")+1).toLowerCase());
			}
		}
	}
	
	private void send302(String url) {
		try {
			String msg="HTTP/1.1 302 Moved Temporarily\r\nContent-Type:text/html;charset=utf-8\r\nLocation:" + url + "/\r\n\r\n";
			os.write(msg.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 读取指定的资源
	 * @param fl
	 * @return
	 */
	private byte[] readFile(File fl) {
		try(FileInputStream fis=new FileInputStream(fl)){
			byte[] bt=new byte[fis.available()];
			fis.read(bt);
			return bt;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void send200(byte[] bt, String extension) {
		try {
			String contentType=ParseXml.getContentType(extension);
			String msg="HTTP/1.1 200 OK\r\nContent-Type:" + contentType + "\r\nContent-Length:" + bt.length + "\r\n\r\n"; 
			os.write(msg.getBytes());
			os.write(bt);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 报错404
	 * @param url
	 */
	private void error404(String url) {
		try {
			String errInfo="<h1>HTTP Status 404 -" + url + "</h1>";
			String msg="HTTP/1.1 404 File Not Found\r\nContent-Type:text/html;charset=utf-8\r\nContent-Length:"+errInfo.length() + "\r\n\r\n"+errInfo;
			os.write(msg.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
