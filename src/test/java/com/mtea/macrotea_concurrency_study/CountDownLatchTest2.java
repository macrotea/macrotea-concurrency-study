/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package com.mtea.macrotea_concurrency_study;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * @author liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2013-1-7 下午8:23:09
 */
public class CountDownLatchTest2 {
	
	//例如：百米赛跑：8名运动员同时起跑，由于速度的快慢，肯定有会出现先到终点和晚到终点的情况，而终点有个统计成绩的仪器，当所有选手到达终点时，它会统计所有人的成绩并进行排序，然后把结果发送到汇报成绩的系统。 

	@Test
	public void test1() throws InterruptedException {
		CountDownLatch begSignal = new CountDownLatch(1);
		CountDownLatch endSignal = new CountDownLatch(8);

		for (int i = 0; i < 8; i++) {
			new Thread(new Work(i, begSignal, endSignal)).start();
		}

		try {
			begSignal.countDown(); // 统一起跑
			endSignal.await(); // 等待运动员到达终点
			System.out.println("结果发送到汇报成绩的系统");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Work implements Runnable {
	private int id;
	private CountDownLatch beginSignal;
	private CountDownLatch endSignal;

	public Work(int id, CountDownLatch begin, CountDownLatch end) {
		this.id = id;
		this.beginSignal = begin;
		this.endSignal = end;
	}

	@Override
	public void run() {
		try {
			beginSignal.await();
			System.out.println("起跑...");
			System.out.println("work" + id + "到达终点");
			endSignal.countDown();
			System.out.println("work" + id + "继续干其他事情");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
