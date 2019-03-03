package org.zer0.pocs.reactivex.rxjava.schedulers.tipos;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulerComputationThread_secuencial {

	// En la pc donde se probo(PC de 2 cpus), al invocarse 3 tareas secuenciales,  
	// la primera tarea corre bajo el cpu 1(RxComputationThreadPool-1)
	// la segunda tarea corre bajo el cpu 2(RxComputationThreadPool-2)
	// y la 3era tarea vuelve a correr sobre el cpu 1(RxComputationThreadPool-1)
	// Es decir maximo se crearon 2 hilos pues la pc tiene 2 procesadores.
	public static void main(String[] args) throws Exception{
		TestSchedulerComputationThread_secuencial test = new TestSchedulerComputationThread_secuencial();
		test.testScheduler();
		test.testScheduler();
		test.testScheduler();
	}
	
	private void testScheduler() throws InterruptedException {
		Observable<String> observable = 
			Observable.<String>just("este","es","un","mensaje","en","varias","cadenas.");
		observable.
		doOnNext(t->System.out.println("Thead name:"+Thread.currentThread().getName())).
		subscribeOn(Schedulers.computation()).
		subscribe(System.out::println);
		Thread.sleep(3000);
	}
	
	
}
