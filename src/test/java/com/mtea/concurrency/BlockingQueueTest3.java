/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package com.mtea.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2013-1-14 下午2:32:58
 */
public class BlockingQueueTest3 {

	// add(E e)
	// 将指定元素插入此队列中（如果立即可行且不会违反容量限制），成功时返回 true，如果当前没有可用的空间，则抛出
	// IllegalStateException。
	
	//add : 	插入,返回true/false或者抛出异常
	//offer : 	插入,抛出异常
	//put : 	插入且等待
	
	//take : 	获得队头且等待

	@Test
	public void add1() {
		int capacity = 3;

		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(capacity);

		bq.add("1");
		bq.add("2");
		bq.add("3");
	}

	@Test(expected = IllegalStateException.class)
	public void add2() {
		int capacity = 3;

		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(capacity);

		bq.add("1");
		bq.add("2");
		bq.add("3");

		// overflow
		bq.add("4");
	}

	@Test
	public void drainTo1() {
		int capacity = 3;

		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(capacity);

		bq.add("1");
		bq.add("2");
		bq.add("3");

		List<String> list = new ArrayList<String>();
		bq.drainTo(list);
		
		Assert.assertTrue(0 == bq.size());
		Assert.assertTrue(capacity == list.size());
	}
	
	//drain : 排水
	@Test
	public void drainTo2() {
		int capacity = 3;

		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(capacity);

		bq.add("1");
		bq.add("2");
		bq.add("3");

		int max = 2;
		List<String> list = new ArrayList<String>();
		bq.drainTo(list, max);

		//还剩3
		Assert.assertTrue(1 == bq.size());
		Assert.assertFalse(bq.contains("1"));
		Assert.assertFalse(bq.contains("2"));
		Assert.assertTrue(bq.contains("3"));
		
		Assert.assertTrue(max == list.size());
	}
	
	@Test
	public void offer1() {
		int capacity = 3;
		
		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(capacity);
		
		bq.add("1");
		bq.add("2");
		
		Assert.assertTrue(bq.offer("3"));
		Assert.assertFalse(bq.offer("4"));
	}
	
	@Test
	public void offer2() {
		int capacity = 3;
		
		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(capacity);
		
		bq.add("1");
		bq.add("2");
		
		Assert.assertTrue(bq.offer("3"));
		Assert.assertFalse(bq.offer("4"));
	}
	
	@Test
	public void take1() throws InterruptedException {
		int capacity = 3;
		
		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(capacity);
		
		bq.add("1");
		bq.add("2");
		bq.add("3");
		
		Assert.assertEquals(bq.take(),"1");
		Assert.assertTrue(2 == bq.size());
		
		Assert.assertEquals(bq.take(),"2");
		Assert.assertTrue(1 == bq.size());
	}

}
