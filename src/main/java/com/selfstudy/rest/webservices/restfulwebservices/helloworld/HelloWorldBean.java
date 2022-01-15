package com.selfstudy.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String message) {
		System.out.println("In HelloWorldBean Constructor");
		this.message = message;
	}

	public String getMessage() {
		System.out.println("In getMessage Getter");
		return message;
	}
	

	public void setMessage(String message) {
		this.message = message;
	}

//	@Override
//	public String toString() {
//		System.out.println("In toSTring()");
//		return String.format("HelloWorldBean [message=%s]", message);
//	}

}
