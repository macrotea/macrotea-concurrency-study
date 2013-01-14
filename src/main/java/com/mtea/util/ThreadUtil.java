package com.mtea.util;


/**
 * 线程工具类
 * @author 	liangqiye@gz.iscas.ac.cn
 * @version 1.0 , 2013-1-2 下午3:52:50
 */
public class ThreadUtil {
	
	public static final int SECONDS=1000;

	/**
	 * 睡眠
	 * @param i
	 * @author liangqiye / 2013-1-2 下午3:52:52
	 */
	public static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException ignore) {
			ignore.printStackTrace();
		}
	}
	
	/**
	 * 睡眠/秒
	 * @param i
	 * @author liangqiye / 2013-1-2 下午3:52:52
	 */
	public static void sleepSeconds(int i) {
		try {
			Thread.sleep(i * SECONDS);
		} catch (InterruptedException ignore) {
			ignore.printStackTrace();
		}
	}

}