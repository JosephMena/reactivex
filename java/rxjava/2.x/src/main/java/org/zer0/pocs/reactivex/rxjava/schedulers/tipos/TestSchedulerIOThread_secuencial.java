package org.zer0.pocs.reactivex.rxjava.schedulers.tipos;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulerIOThread_secuencial {

	// En este ejemplo se puede apreciar que ante 3 invocaciones se utliza el mismo hilo 
	// RxCachedThreadScheduler
	public static void main(String[] args) throws Exception{
		TestSchedulerIOThread_secuencial test = new TestSchedulerIOThread_secuencial();
		test.testScheduler();
		test.testScheduler();
		test.testScheduler();
	}
	
	private void testScheduler() throws InterruptedException {
		Observable<String> observable = 
			Observable.<String>just("este","es","un","mensaje","en","varias","cadenas.");
		observable.
		doOnNext(t->System.out.println("Thead name:"+Thread.currentThread().getName())).
		subscribeOn(Schedulers.io()).
		subscribe(System.out::println);
		Thread.sleep(3000);
	}
	
	
}
