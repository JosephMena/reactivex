package org.zer0.pocs.reactivex.rxjava.schedulers.tipos;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulerSingleThread_secuencial {

	// En este ejemplo se puede apreciar que ante 3 invocaciones solo se crea 1 hilo, el cual
	// es compartido por los distintas tareas.
	public static void main(String[] args) throws Exception{
		TestSchedulerSingleThread_secuencial test = new TestSchedulerSingleThread_secuencial();
		test.testScheduler();
		test.testScheduler();
		test.testScheduler();
	}
	
	private void testScheduler() throws InterruptedException {
		Observable<String> observable = 
			Observable.<String>just("este","es","un","mensaje","en","varias","cadenas.");
		observable.
		doOnNext(t->System.out.println("Thead name:"+Thread.currentThread().getName())).
		subscribeOn(Schedulers.single()).
		subscribe(System.out::println);
		Thread.sleep(3000);
	}
	
	
}
