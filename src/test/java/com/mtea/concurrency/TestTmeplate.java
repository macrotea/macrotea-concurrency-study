/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package com.mtea.concurrency;

import java.util.ConcurrentModificationException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2013-1-14 下午2:36:11
 */
public class TestTmeplate {

	@Test
	public void test1() {
		int a = 3;
		int b = 3;
		Assert.assertTrue(a == b);
	}

	@Test(expected = ConcurrentModificationException.class)
	public void test2() {

	}
}
