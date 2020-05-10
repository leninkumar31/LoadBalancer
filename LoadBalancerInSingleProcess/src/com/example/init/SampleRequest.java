package com.example.init;

import com.example.loadbalancer.AbstractRequest;

public class SampleRequest extends AbstractRequest<Integer, Double> {
	
	public SampleRequest(Integer request) {
		super(request);
	}

	public void execute() {
		System.out.println(Math.sqrt(getRequest()));
		setResponse(Math.sqrt(getRequest()));
	}
}
