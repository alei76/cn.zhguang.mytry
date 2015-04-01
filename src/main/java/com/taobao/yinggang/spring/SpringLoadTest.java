package com.taobao.yinggang.spring;

import org.junit.Test;

import com.taobao.itest.ITestSpringContextBaseCase;
import com.taobao.itest.annotation.ITestSpringContext;

@ITestSpringContext(locations = { "classpath:bean/beans.xml" })
public class SpringLoadTest extends ITestSpringContextBaseCase {

	@Test
	public void testBeanLoad() {

		BeanA beana = new BeanA();
		beana.init();
		System.out.println("beanLoad...");
	}
}
