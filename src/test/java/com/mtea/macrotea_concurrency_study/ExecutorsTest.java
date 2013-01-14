/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package com.mtea.macrotea_concurrency_study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2013-1-4 下午4:49:05
 */
public class ExecutorsTest {

	/**
	 * 一共运行5条线程
	 * 每次以固定的3条运行
	 * 分两次运行
	 * 每次运行接近5秒
	 * 故而awaitTermination的15秒足够for循环中的线程运行完毕
	 * @throws InterruptedException
	 * @author liangqiye / 2013-1-4 下午4:58:28
	 */
	//@Test
	public void newFixedThreadPool() throws InterruptedException {

		// 3条线程
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		final int count = 5;
		for (int i = 1; i <= count; i++) {
			final int index = i;
			executorService.execute(new Runnable() {
				public void run() {
					System.out.println("线程运行: " + index);
					ThreadUtil.sleepSeconds(5);
					System.out.println("线程结束: " + index);
				}
			});
		}
		
		System.out.println("是否executorService已经Shutdown : "+executorService.isShutdown());
		executorService.awaitTermination(15, TimeUnit.SECONDS);
		System.out.println("是否executorService已经Termination : "+executorService.isTerminated());
	}
	
	@Test
	public void newSingleThreadExecutor() throws InterruptedException {
		
		// 仅仅1条线程
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		final int count = 5;
		for (int i = 1; i <= count; i++) {
			final int index = i;
			executorService.execute(new Runnable() {
				public void run() {
					System.out.println("线程运行: " + index);
					ThreadUtil.sleepSeconds(1);
					System.out.println("线程结束: " + index);
				}
			});
		}
		executorService.awaitTermination(7, TimeUnit.SECONDS);
		
		int a = 0;
		while (true) {
			a++;
			
//			if(a==6){
//				executorService.shutdown();
//			}
			
			if (a > 10) {
				break;
			} else {
				ThreadUtil.sleepSeconds(1);
				System.out.println("是否executorService已经Termination :\t"+executorService.isTerminated());
				System.out.println("是否executorService已经Shutdown :\t"+executorService.isShutdown());
			}
		}
	}

}
