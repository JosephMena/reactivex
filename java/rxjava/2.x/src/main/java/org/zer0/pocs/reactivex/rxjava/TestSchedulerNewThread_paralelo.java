package org.zer0.pocs.reactivex.rxjava;

public class TestSchedulerNewThread_paralelo {

	//En este ejemplo se puede apreciar que ante 3 invocaciones se crean 3 hilos distintos, aunque
	//estas invocaciones sean secuencialmente.
	public static void main(String[] args) throws Exception{
		for(int i=0;i<4;i++) {
			HiloObservable h = new HiloObservable();
			Thread t=new Thread(h);
			t.start();
		}
		Thread.sleep(5000);
	}
	
	
}
