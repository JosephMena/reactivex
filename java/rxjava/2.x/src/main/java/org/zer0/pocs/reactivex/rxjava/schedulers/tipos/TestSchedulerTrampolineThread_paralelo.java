package org.zer0.pocs.reactivex.rxjava.schedulers.tipos;

public class TestSchedulerTrampolineThread_paralelo {

	// En este ejemplo se puede apreciar que las 4 tareas, ejecutadas en "paralelo", 
	// son ejecutadas bajo 4 hilos dinstintos. 
	// 
	public static void main(String[] args) throws Exception{
		for(int i=0;i<4;i++) {
			HiloTrampolineThreadObservable h = new HiloTrampolineThreadObservable();
			Thread t=new Thread(h);
			t.start();
		}
		Thread.sleep(5000);
	}
	
	
}
