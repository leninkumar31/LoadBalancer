package com.example.loadbalancer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable, Comparable<Worker> {
	int bufferSize;
	int completed;
	int pending;
	BlockingQueue<AbstractRequest> workerQueue;

	public Worker(int bufferSize) {
		this.bufferSize = bufferSize;
		this.workerQueue = new ArrayBlockingQueue<>(bufferSize);
	}
	
	public void addToWorkerQueue(AbstractRequest request){
		try {
			workerQueue.put(request);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pending++;
	}

	@Override
	public void run() {
		while (true) {
			AbstractRequest currRequest;
			try {
				currRequest = workerQueue.take();
				currRequest.execute();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			completed++;
		}
	}

	@Override
	public int compareTo(Worker o) {
		return this.pending - o.pending;
	}
}
