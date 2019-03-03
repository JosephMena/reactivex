package org.zer0.pocs.reactivex.rxjava.schedulers.tipos;

public class TestSchedulerIOThread_paralelo {

	// En este ejemplo se puede apreciar que las 4 tareas, ejecutadas en paralelo, 
	// son ejecutadas en 4 hilos distintos. 
	public static void main(String[] args) throws Exception{
		for(int i=0;i<4;i++) {
			HiloIOObservable h = new HiloIOObservable();
			Thread t=new Thread(h);
			t.start();
		}
		Thread.sleep(5000);
	}
	
	
}
