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
public class SubClass2 extends SuperClass {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	@BeforeClass
	public static void beforeSubClass() {
		System.out.println("beforeSubClass2");
	}

	@Before
	public void beforeSub() {
		System.out.println("beforeSub2");
	}

	@After
	public void afterSub() {
		System.out.println("afterSub2");
	}

	@AfterClass
	public static void afterSubClass() {
		System.out.println("afterSubClass2");
	}

	@Test
	public void test2() {
		System.out.println("subclass2: test2");
	}
}
