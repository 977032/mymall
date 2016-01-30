package com.ffyc.server.action;

import java.util.UUID;

public class TestAction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id="";
		id= UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(id);
	}

}
