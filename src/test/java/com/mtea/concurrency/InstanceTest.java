package com.mtea.concurrency;

import org.junit.Test;

/**
 * @author macrotea@qq.com
 * @date 2013-1-7 下午9:48:44
 * @version 1.0
 * @note
 */
public class InstanceTest {

	class Car {

	}

	class BMW extends Car {

	}

	@Test
	public void test1() {

		System.out.println("new BMW() instanceof BMW : " + (new BMW() instanceof BMW));

		System.out.println("new BMW() instanceof Car : " + (new BMW() instanceof Car));

		System.out.println("BMW.class.isAssignableFrom(Car.class) : " + (BMW.class.isAssignableFrom(Car.class)));

		System.out.println("BMW.class.isAssignableFrom(BMW.class) : " + (BMW.class.isAssignableFrom(BMW.class)));

		System.out.println("Car.class.isAssignableFrom(BMW.class) : " + (Car.class.isAssignableFrom(BMW.class)));

		System.out.println("BMW.class.isInstance(new BMW()) : " + (BMW.class.isInstance(new BMW())));
		
		System.out.println("Car.class.isInstance(new BMW()) : " + (Car.class.isInstance(new BMW())));
	}
}
