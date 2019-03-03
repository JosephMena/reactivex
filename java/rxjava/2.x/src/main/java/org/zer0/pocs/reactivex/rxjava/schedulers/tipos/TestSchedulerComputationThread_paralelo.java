package org.zer0.pocs.reactivex.rxjava.schedulers.tipos;

public class TestSchedulerComputationThread_paralelo {

	// En este ejemplo se puede apreciar que las 4 tareas, ejecutadas en "paralelo", 
	// son ejecutadas bajo 2 hilos, esto por que la PC solo tiene 2 cpus.
	public static void main(String[] args) throws Exception{
		for(int i=0;i<4;i++) {
			HiloComputationObservable h = new HiloComputationObservable();
			Thread t=new Thread(h);
			t.start();
		}
		Thread.sleep(5000);
	}
	
	
}
