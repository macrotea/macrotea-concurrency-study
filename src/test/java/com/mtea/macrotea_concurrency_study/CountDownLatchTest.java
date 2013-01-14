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
public class CountDownLatchTest {

	@Test
	public void await1() throws InterruptedException {
		
		//倒数阀初始化值为2,当它的值为0的时候,await()的等待才被打断
		final CountDownLatch latch = new CountDownLatch(2);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(" await1() - Start thread 1");
				ThreadUtil.sleep(1000);
				System.out.println(" await1() - End thread 1");
				
				//减1
				latch.countDown();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(" await1() - Start thread 2");
				ThreadUtil.sleep(1000);
				System.out.println(" await1() - End thread 2");
				
				//减1
				latch.countDown();
			}
		}).start();
		
		latch.await();

		System.out.println("我要等所有线程运行完才能打印出来");
	}
	
	
	//@Test
	public void await2() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(4);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(" await2() - Start thread 1");
				ThreadUtil.sleep(1000);
				System.out.println(" await2() - End thread 1");
				
				//减到0了,故而await()放行
				latch.countDown();
				latch.countDown();
				latch.countDown();
				latch.countDown();
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(" await2() - Start thread 2");
				ThreadUtil.sleep(5000);
				System.out.println(" await2() - End thread 2");
				latch.countDown();
			}
		}).start();
		
		latch.await();
		
		System.out.println("我要等[ thread 1 ]运行完才能打印出来");
	}

}
