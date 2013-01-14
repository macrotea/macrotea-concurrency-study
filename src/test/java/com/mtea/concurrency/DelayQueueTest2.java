package com.mtea.concurrency;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest2 {

	public static void main(String args[]) {
		DelayQueue<DelayTask> queue = new DelayQueue<DelayTask>();
		for (int i = 0; i < 20; i++) {
			long delay = (long) (Math.random() * 20000);
			
			//DelayTask 的getDelay很关键
			queue.offer(new DelayTask(delay, queue, i));
		}
		Executors.newCachedThreadPool().execute(new YConsumer(queue));
	}
}

class YConsumer implements Runnable {
	DelayQueue<DelayTask> q;

	public YConsumer(DelayQueue<DelayTask> _q) {
		q = _q;
	}

	public void run() {
		while (true) {
			try {
				System.out.println((DelayTask) (q.take()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * DelayTask基于优先级队列(故而重写compareTo())和Delayed接口
 * @author 	liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2013-1-14 下午5:45:25
 */
class DelayTask implements Delayed {
	private static int count = 0;
	private int id = count++;
	private long nanoTime = System.nanoTime();
	private long delayInMilliseconds;
	private long triger;
	private DelayQueue<DelayTask> q;
	private int order;

	public DelayTask(long _delay, DelayQueue<DelayTask> _q, int _order) {
		order = _order;
		q = _q;
		this.delayInMilliseconds = _delay;
		triger = nanoTime + TimeUnit.NANOSECONDS.convert(_delay, TimeUnit.MILLISECONDS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
	 */
	public long getDelay(TimeUnit unit) {
		return triger - System.nanoTime();
	}

	public String toString() {
		return "Task#" + id + " in index " + order + ": delaying " + delayInMilliseconds + " miliseconds picked up.";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Delayed o) {
		DelayTask delayTask = (DelayTask) o;
		return triger > delayTask.triger ? 1 : triger < delayTask.triger ? -1 : 0;
	}

	/**
	 * 获得 q
	 * @return 
	 */
	public DelayQueue<DelayTask> getQ() {
		return q;
	}

	/**
	 * 设置 q
	 * @param q
	 */
	public void setQ(DelayQueue<DelayTask> q) {
		this.q = q;
	}
	
}
