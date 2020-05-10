package com.example.init;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class WorkGenerator implements Runnable {
	BlockingQueue<SampleRequest> queue;
	WorkGenerator(BlockingQueue<SampleRequest> queue){
		this.queue = queue;
	}
	@Override
	public void run(){
		Random rand = new Random();
		while(true){
			SampleRequest request = new SampleRequest(rand.nextInt(1000));
			try {
				queue.put(request);
				Thread.sleep((long) (Math.random()*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
