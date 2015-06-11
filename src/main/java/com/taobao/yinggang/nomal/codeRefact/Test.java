package com.taobao.yinggang.nomal.codeRefact;

import java.util.Date;

public class Test extends Date {
	// public SmsRequest fun1(SmsMessage sms){
	// SmsRequest req = (SmsRequest)sms.receive();
	// return req;
	// }
	// public MmsRequest fun2(MmsMessage mms){
	// MmsRequest req = (MmsRequest)mms.receive();
	// return req;
	// }
	// TestInterface testInterface = new TestIClass();
	//
	// String a = TestIClass.testStr;
	public static void main(String[] args) {
		new Test().test();
	}

	public void test() {
		try {
			throw new ClassCastException();
		} catch (ClassCastException e) {

		}

		System.out.println(super.getClass().getName());
	}
}
