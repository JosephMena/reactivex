package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulerNewThread_secuencial {

	//En este ejemplo se puede apreciar que ante 3 invocaciones se crean 3 hilos distintos, aunque
	//estas invocaciones sean secuencialmente.
	public static void main(String[] args) throws Exception{
		TestSchedulerNewThread_secuencial test = new TestSchedulerNewThread_secuencial();
		test.testScheduler();
		test.testScheduler();
		test.testScheduler();
	}
	
	private void testScheduler() throws InterruptedException {
		Observable<String> observable = 
			Observable.<String>just("este","es","un","mensaje","en","varias","cadenas.");
		observable.
		doOnNext(t->System.out.println("Thead name:"+Thread.currentThread().getName())).
		subscribeOn(Schedulers.newThread()).
		subscribe(System.out::println);
		Thread.sleep(3000);
	}
	
	
}
