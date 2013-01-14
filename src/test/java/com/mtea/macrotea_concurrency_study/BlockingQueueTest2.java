/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package com.mtea.macrotea_concurrency_study;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2013-1-14 下午1:24:14
 */
public class BlockingQueueTest2 {

	public static void main(String[] args) {
		BlockingQueue<String> drop = new ArrayBlockingQueue<String>(1, true);
		(new Thread(new Producer(drop))).start();
		(new Thread(new Consumer(drop))).start();
	}

}

class Producer implements Runnable {
	
	private BlockingQueue<String> drop;
	
	List<String> messages = Arrays.asList("Mares eat oats", "Does eat oats", "Little lambs eat ivy", "Wouldn't you eat ivy too?");

	public Producer(BlockingQueue<String> d) {
		this.drop = d;
	}

	public void run() {
		try {
			for (String s : messages)
				drop.put(s);
			drop.put("DONE");
		} catch (InterruptedException intEx) {
			System.out.println("Interrupted! " + "Last one out, turn out the lights!");
		}
	}
}

class Consumer implements Runnable {
	
	private BlockingQueue<String> drop;

	public Consumer(BlockingQueue<String> d) {
		this.drop = d;
	}

	public void run() {
		try {
			String msg = null;
			while (!((msg = drop.take()).equals("DONE")))
				System.out.println(msg);
		} catch (InterruptedException intEx) {
			System.out.println("Interrupted! " + "Last one out, turn out the lights!");
		}
	}
}
