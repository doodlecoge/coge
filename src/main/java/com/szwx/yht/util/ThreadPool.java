package com.szwx.yht.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 控制线程量
 * @author zhangyj
 * @date Mar 28, 2012
 */
public class ThreadPool {

	private static Executor executor;
	private static final int size=100;
	
	static {
		executor = Executors.newFixedThreadPool(size);
	}

	public static void execute(Runnable run) {
		executor.execute(run);
	}
}
