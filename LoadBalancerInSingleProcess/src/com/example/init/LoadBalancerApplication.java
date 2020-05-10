package com.example.init;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import com.example.loadbalancer.Balancer;

public class LoadBalancerApplication {
	public static final int numberOfWorkers = 10;
	public static final int bufferSize = 100;
	public static void main(String[] args) {
		// Create a queue for insertions and retrievals
		BlockingQueue<SampleRequest> requestQueue = new SynchronousQueue<>();
		// Generate the work at irregular intervals
		Thread generator = new Thread(new WorkGenerator(requestQueue));
		generator.start();
		Balancer.InitializeBalancer(numberOfWorkers, bufferSize).balanceWork(requestQueue);
	}
}
