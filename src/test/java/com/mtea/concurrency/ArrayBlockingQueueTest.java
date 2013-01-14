/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package com.mtea.concurrency;

/**
 * @author 	liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2013-1-14 下午4:47:02
 */
public class ArrayBlockingQueueTest {
	
	//1.在ArrayBlockingQueue内部，维护了一个定长数组
	
	//2.ArrayBlockingQueue在生产者放入数据和消费者获取数据，都是共用同一个锁对象，由此也意味着两者无法真正并行运行
	//LinkedBlockingQueue生产者端和消费者端分别采用了独立的锁来控制数据同步，这也意味着在高并发的情况下生产者和消费者可以并行地操作队列中的数据
	
	//3.

}
