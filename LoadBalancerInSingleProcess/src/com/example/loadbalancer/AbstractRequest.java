package com.example.loadbalancer;

public abstract class AbstractRequest<T, U> {
	T request;
	U response;
	
	protected AbstractRequest(T request){
		this.request = request;
	}
	
	public abstract void execute();
	
	public T getRequest() {
		return request;
	}
	
	public void setRequest(T request) {
		this.request = request;
	}
	
	public U getResponse() {
		return response;
	}
	
	public void setResponse(U response) {
		this.response = response;
	}
}
