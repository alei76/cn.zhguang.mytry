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

/**
 * @author egugzhg
 * @version $Revision: $
 */
public class SuperClass {
	/** Revision of the class */
	public static final String _REV_ID_ = "$Revision: $";

	@BeforeClass
	public static void beforeSuperClass() {
		System.out.println("beforeSuperClass");
	}

	@Before
	public void beforeSuper() {
		System.out.println("beforeSuper");
	}

	@After
	public void afterSuper() {
		System.out.println("afterSuper");
	}

	@AfterClass
	public static void afterSuperClass() {
		System.out.println("afterSuperClass");
	}
}
