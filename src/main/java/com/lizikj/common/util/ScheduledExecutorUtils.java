package com.lizikj.common.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledExecutorUtils {

	/**
	 * 线程池
	 */
	private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());//长度cpu核数 
	
	
	public static ScheduledExecutorService getExecutorService(){
		return executorService;
	}
}
