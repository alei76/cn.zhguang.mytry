/*
 * $HeadURL: $
 * $Id: $
 * Copyright (c) 2011 by Ericsson, all rights reserved.
 */

package com.taobao.yinggang.nomal.junittry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author egugzhg
 * @version $Revision: $
 */
public class SubClass1 extends SuperClass {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	@BeforeClass
	public static void beforeSubClass() {
		System.out.println("beforeSubClass1");
	}

	@Before
	public void beforeSub() {
		System.out.println("beforeSub1");
	}

	@After
	public void afterSub() {
		System.out.println("afterSub1");
	}

	@AfterClass
	public static void afterSubClass() {
		System.out.println("afterSubClass1");
	}

	@Test
	public void test1() {
		System.out.println("subclass1: test1");
	}
}
