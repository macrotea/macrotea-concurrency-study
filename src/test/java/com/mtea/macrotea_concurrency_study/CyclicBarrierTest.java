package com.mtea.macrotea_concurrency_study;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

/**
 * @author macrotea@qq.com
 * @date 2013-1-7 下午10:39:20
 * @version 1.0
 * @note
 */
public class CyclicBarrierTest {

	//有四个游戏玩家玩游戏，游戏有三个关卡，每个关卡必须要所有玩家都到达后才能允许通关。 W
	
	@Test
	public void test1() {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {

			@Override
			public void run() {
				System.out.println("所有玩家进入第二关！");
			}
		});

		for (int i = 0; i < 4; i++) {
			new Thread(new Player(i, cyclicBarrier)).start();
		}
	}

	class Player implements Runnable {

		private CyclicBarrier cyclicBarrier;
		private int id;

		public Player(int id, CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
			this.id = id;
		}

		@Override
		public void run() {
			try {
				System.out.println("玩家" + id + "正在玩第一关...");
				cyclicBarrier.await();
				System.out.println("玩家" + id + "进入第二关...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

}
