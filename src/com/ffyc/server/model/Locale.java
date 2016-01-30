package com.ffyc.server.model;

import java.io.Serializable;

public class Locale implements Serializable {
	
	private static final long serialVersionUID = 8331342657283724154L;
	private String id;
	private String name;

	public Locale() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
