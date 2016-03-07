package com.javachina.kit;

import java.util.concurrent.Executors;

public final class TaskKit {
	
	private TaskKit() {
	}
	
	public static void run(Runnable t){
		Executors.newSingleThreadExecutor().submit(t);
	}
	
}