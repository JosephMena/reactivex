package org.zer0.pocs.reactivex.rxjava.schedulers.tipos;

public class TestSchedulerDefaultThread_paralelo {

	// En este ejemplo se puede apreciar que las 4 tareas, ejecutadas en paralelo, 
	// son ejecutadas en 4 hilos distintos, pero no se esta usando el metodo schedulers.
	// Corren bajo el hilo que fue creado explicitamente.
	public static void main(String[] args) throws Exception{
		for(int i=0;i<4;i++) {
			HiloDefaultObservable h = new HiloDefaultObservable();
			Thread t=new Thread(h);
			t.start();
		}
		Thread.sleep(5000);
	}
	
	
}
