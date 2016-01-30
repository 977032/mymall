package com.ffyc.server.action;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

public class BaseAction extends SuperAction implements ServletRequestAware,
		ServletResponseAware, SessionAware {
	private static final long serialVersionUID = 1246108579045614814L;
	protected Logger logger = Logger.getLogger(getClass());
}
