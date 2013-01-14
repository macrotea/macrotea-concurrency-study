/*
 * Copyright (C) 2012 GZ-ISCAS Inc., All Rights Reserved.
 */
package com.mtea.concurrency;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author 	liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2013-1-12 下午3:24:14
 */
public class TimeUnitTest {

	@Test
	public void toHours(){
		Assert.assertEquals(TimeUnit.DAYS.toHours(1) ,24);
		Assert.assertEquals(TimeUnit.DAYS.toHours(2) ,48);
		Assert.assertEquals(TimeUnit.DAYS.toHours(3) ,72);
	}
	
	@Test
	public void toMinutes(){
		Assert.assertEquals(TimeUnit.HOURS.toMinutes(1) ,60);
		Assert.assertEquals(TimeUnit.HOURS.toMinutes(2) ,120);
		Assert.assertEquals(TimeUnit.HOURS.toMinutes(3) ,180);
	}
	
	@Test
	public void toMinutes2(){
		Assert.assertEquals(TimeUnit.SECONDS.toMinutes(3600) ,60);
		Assert.assertEquals(TimeUnit.SECONDS.toMinutes(60) ,1);
	}
	
	@Test
	public void convert(){
		Assert.assertEquals(TimeUnit.SECONDS.convert(1L, TimeUnit.MINUTES), 60);
		Assert.assertEquals(TimeUnit.MINUTES.convert(2L, TimeUnit.HOURS), 120);
		Assert.assertEquals(2*TimeUnit.MINUTES.convert(2L, TimeUnit.HOURS), 240);
	}
	
}
