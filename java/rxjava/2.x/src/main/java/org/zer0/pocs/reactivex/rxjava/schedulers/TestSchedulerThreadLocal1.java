package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulerThreadLocal1 {

	private ThreadLocal<String> dni = new ThreadLocal<>(); 
	
	public static void main(String[] args) throws Exception {
		TestSchedulerThreadLocal1 test = new TestSchedulerThreadLocal1();
		test.testThreadLocal();
		Thread.sleep(3000);
	}
	
	private void testThreadLocal() {
		Single.just("mensaje")
		.map(dato->{
			dni.set("25413025");
			return dato;})
		.map(dato2->{
			System.out.println("dni:"+dni.get());
			return dato2;
			})
		.subscribeOn(Schedulers.io())
		.subscribe(System.out::println);
	}
	
}
