package com.mtea.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author macrotea@qq.com
 * @date 2012-12-22 下午1:03:26
 * @version 1.0
 * @note
 */
public class MultiThreadAddAndRemoveResolution_Fail_1 {
	
	// FIXME macrotea@qq.com / 2013-9-14 20:14:15 
	List<Long> list = Collections.synchronizedList(new ArrayList<Long>());
	
	Thread addThread = new Thread(new Runnable() {

		@Override
		public void run() {

			while (true) {
				int count = random.nextInt(5);
				for (int i = 0; i < count; i++) {
					list.add(random.nextLong());
				}
				System.out.println("addThread size : " + list.size());
				sleep();
			}

		}

	});

	Thread removeThread = new Thread(new Runnable() {

		@Override
		public void run() {

			while (true) {
				int size = list.size();
				if(size==0){
					 sleep();continue;
				}
				int index = random.nextInt(size);
				Long target = list.get(index);
				for (Iterator<Long> iterator = list.iterator(); iterator.hasNext();) {
					Long each = iterator.next();
					if (each.longValue() == target.longValue()) {
						// FIXME macrotea@qq.com / 2013-9-14 20:14:19 
						iterator.remove();
						break;
					}
				}
				System.out.println("removeThread size : " + list.size());

				sleep();
			}

		}

	});

	public static void main(String[] args) {
		new MultiThreadAddAndRemoveResolution_Fail_1().start();
	}

	public void start() {
		addThread.start();
		removeThread.start();
	}

	Random random = new Random();

	private void sleep() {
		try {
			Thread.sleep(random.nextInt(10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
