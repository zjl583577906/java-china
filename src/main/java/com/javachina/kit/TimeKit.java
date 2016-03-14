package com.javachina.kit;

import java.util.Calendar;

public class TimeKit {
	
	public static Integer getTodayTime() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(today.getTimeInMillis()).substring(0, 10));
	}
	
	public static Integer getYesterdayTime() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, -24);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(today.getTimeInMillis()).substring(0, 10));
	}

	public static Integer getTomorrowTime() {
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.set(Calendar.HOUR_OF_DAY, 24);
		tomorrow.set(Calendar.MINUTE, 0);
		tomorrow.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(tomorrow.getTimeInMillis()).substring(0, 10));
	}
	
}