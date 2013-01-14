/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package com.mtea.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.junit.Test;

import com.mtea.util.ThreadUtil;

/**
 * @author liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2012-12-21 下午3:29:52
 */
public class SemaphoreAccessWCThreadTest {
	
	int accessWCThreadFinishCount=0;
	int userTotal=20;

	// 假如有20个人要上5个厕所
	@Test
	public void testSemaphore1() {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		
		// 最多只能5个线程同时访问厕所
		final Semaphore semp = new Semaphore(5);
		
		// 模拟20个人访问厕所
		for (int i = 1; i <= userTotal; i++) {
			exec.execute(new AccessWCThread(i, semp));
		}

		while (true) {
			if (accessWCThreadFinishCount == userTotal) {
				// 退出线程池
				exec.shutdown();
				break;
			} else {
				ThreadUtil.sleep(100);
			}
		}
	}

	/**
	 * 访问厕所线程
	 * 
	 * @author liangqiye@gz.iscas.ac.cn
	 * @version 1.0 , 2012-12-21 下午3:37:14
	 */
	class AccessWCThread extends Thread {

		/**
		 * 用户Id
		 */
		private int userId;

		/**
		 * 信号量
		 */
		private Semaphore semp;

		/**
		 * @param userId
		 * @param semp
		 */
		public AccessWCThread(int userId, Semaphore semp) {
			super();
			this.userId = userId;
			this.semp = semp;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			try {
				// 获取许可
				semp.acquire();
				System.out.println(String.format("第%s号人【访问】厕所", userId));
				
				Thread.sleep((long) (Math.random() * 100));
				
				// 访问完后，释放
				semp.release();
				
				accessWCThreadFinishCount++;
				System.out.println(String.format("第%s号人【离开】厕所", userId));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 获得 userId
		 * @return
		 */
		public int getUserId() {
			return userId;
		}

		/**
		 * 设置 userId
		 * @param userId
		 */
		public void setUserId(int userId) {
			this.userId = userId;
		}

		/**
		 * 获得 semp
		 * @return
		 */
		public Semaphore getSemp() {
			return semp;
		}

		/**
		 * 设置 semp
		 * @param semp
		 */
		public void setSemp(Semaphore semp) {
			this.semp = semp;
		}

	}

}
