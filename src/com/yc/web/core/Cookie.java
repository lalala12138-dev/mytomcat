package com.yc.web.core;

import com.yc.tomcat.core.TomcatConstants;

public class Cookie {
	private String name;
	private String vaule;
	private long maxAge; //过期时间
	
	public Cookie(String name,String value) {
		this.name=name;
		this.vaule=value;
		this.maxAge=TomcatConstants.SESSION_TIMEOTU;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVaule() {
		return vaule;
	}

	public void setVaule(String vaule) {
		this.vaule = vaule;
	}

	public long getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(long maxAge) {
		this.maxAge = maxAge;
	}
	
	
}
