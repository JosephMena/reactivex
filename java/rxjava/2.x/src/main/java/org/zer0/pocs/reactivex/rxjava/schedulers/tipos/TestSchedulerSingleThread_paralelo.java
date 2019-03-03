package org.zer0.pocs.reactivex.rxjava.schedulers.tipos;

public class TestSchedulerSingleThread_paralelo {

	// En este ejemplo se puede apreciar que las 4 tareas, ejecutadas en "paralelo", son ejecutadas 
	// bajo un solo hilo.
	public static void main(String[] args) throws Exception{
		for(int i=0;i<4;i++) {
			HiloSingleObservable h = new HiloSingleObservable();
			Thread t=new Thread(h);
			t.start();
		}
		Thread.sleep(5000);
	}
	
	
}
