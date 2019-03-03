package org.zer0.pocs.reactivex.rxjava.schedulers.tipos;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulerTrampolineThread_secuencial {

	// En este ejemplo se puede apreciar que ante 3 invocaciones, se usa un solo hilo  
	// En este caso se usa el hilo principal(main), el unico de todos lo Schedulers
	// que ha utilizado el hilo main
	public static void main(String[] args) throws Exception{
		TestSchedulerTrampolineThread_secuencial test = new TestSchedulerTrampolineThread_secuencial();
		test.testScheduler();
		test.testScheduler();
		test.testScheduler();
	}
	
	private void testScheduler() throws InterruptedException {
		Observable<String> observable = 
			Observable.<String>just("este","es","un","mensaje","en","varias","cadenas.");
		observable.
		doOnNext(t->System.out.println("Thead name:"+Thread.currentThread().getName())).
		subscribeOn(Schedulers.trampoline()).
		subscribe(System.out::println);
		Thread.sleep(3000);
	}
	
	
}
