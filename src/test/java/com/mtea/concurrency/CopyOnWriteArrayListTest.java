/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package com.mtea.concurrency;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2013-1-12 上午10:42:25
 */
public class CopyOnWriteArrayListTest {
	
	//CopyOnWriteArrayList适合使用在读操作远远大于写操作的场景里，比如缓存。
	
	//因为每次使用CopyOnWriteArrayList.add都要引起数组拷贝，或者使用CopyOnWriteArrayList.addAll，避免在循环中使用CopyOnWriteArrayList.add。
	
	//CopyOnWriteArrayList 是个巧妙的小宝贝，能解决这一问题。它的 Javadoc 将 CopyOnWriteArrayList 定义为一个 “ArrayList 的线程安全变体，在这个变体中所有易变操作（添加，设置等）可以通过复制全新的数组来实现”。

	@Test(expected = ConcurrentModificationException.class)
	public void remove1() {
		List<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");

		for (String string : al) {
			if (string.equalsIgnoreCase("a")) {
				al.remove("a");
			}
		}
	}

	@Test(expected = ConcurrentModificationException.class)
	public void remove2() {
		List<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");

		for (String string : al) {
			if (string.equalsIgnoreCase("a")) {
				al.remove(0);
			}
		}
	}

	@Test(expected = ConcurrentModificationException.class)
	public void add1() {
		List<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");

		for (String string : al) {
			if (string.equalsIgnoreCase("a")) {
				al.add("aa");
			}
		}
	}

	@Test
	public void cow_add() {
		List<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");

		// 转换
		List<String> cowList = new CopyOnWriteArrayList<String>(al);

		for (String string : cowList) {
			if (string.equalsIgnoreCase("a")) {
				cowList.add("aa");
			}
		}

		Assert.assertTrue(al.size() == 3);
		Assert.assertTrue(cowList.size() == 4);
		Assert.assertTrue(cowList.contains("aa"));
	}

	@Test
	public void cow_remove() {
		List<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");

		// 转换
		List<String> cowList = new CopyOnWriteArrayList<String>(al);

		for (String string : cowList) {
			if (string.equalsIgnoreCase("a")) {
				cowList.remove("a");
			}
		}

		Assert.assertTrue(al.size() == 3);
		Assert.assertTrue(cowList.size() == 2);
		Assert.assertTrue(cowList.indexOf("a") == -1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void cow_remove_in_iterator() {
		List<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");

		// 转换
		List<String> cowList = new CopyOnWriteArrayList<String>(al);

		// CopyOnWriteArrayList在迭代中中remove反而出错
		for (Iterator<String> iterator = cowList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if (string.equalsIgnoreCase("a")) {
				
				//其实这里的迭代是对原来的对象a1进行迭代
				//从上面的测试已经知道对a1进行remove是有异常的
				iterator.remove();
			}
		}
	}

	@Test
	public void cow_add_in_iterator() {
		List<String> al = new ArrayList<String>();
		al.add("a");
		al.add("b");
		al.add("c");

		// 转换
		List<String> cowList = new CopyOnWriteArrayList<String>(al);

		// CopyOnWriteArrayList在迭代中中remove反而出错
		for (Iterator<String> iterator = cowList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if (string.equalsIgnoreCase("a")) {

				// 注意这里
				cowList.add("aaaa");
			}
		}

		Assert.assertTrue(cowList.size() == 4);
	}

}
