package org.zer0.pocs.reactivex.rxjava.schedulers.tipos;

public class TestSchedulerNewThread_paralelo {

	// En este ejemplo se puede apreciar que ante 4 invocaciones(en paralelo)
	// se crean 3 hilos distintos.
	// Otra cosa que se puede apreciar es que lo items del Observable son procesados secuencialmente. 
	public static void main(String[] args) throws Exception{
		for(int i=0;i<4;i++) {
			HiloNewThreadObservable h = new HiloNewThreadObservable();
			Thread t=new Thread(h);
			t.start();
		}
		Thread.sleep(5000);
	}
	
	
}
