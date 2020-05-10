package com.example.loadbalancer;

import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;

public class Balancer {
	Worker[] workerPool;
	int numWorkers;
	PriorityQueue<Worker> pq;

	private Balancer(int numWorkers) {
		pq = new PriorityQueue<>();
		this.numWorkers = numWorkers;
		workerPool = new Worker[numWorkers];
	}

	public static Balancer InitializeBalancer(int numWorkers, int bufferSize) {
		Balancer balancer = new Balancer(numWorkers);
		for (int i = 0; i < numWorkers; i++) {
			balancer.workerPool[i] = new Worker(bufferSize);
			balancer.pq.add(balancer.workerPool[i]);
			Thread workerThread = new Thread(balancer.workerPool[i]);
			workerThread.start();
		}
		return balancer;
	}

	public void balanceWork(BlockingQueue<? extends AbstractRequest> requestQueue) {
		while (true) {
			try {
				AbstractRequest request = requestQueue.take();
				Worker leastLoadedWorker = pq.peek();
				leastLoadedWorker.addToWorkerQueue(request);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
