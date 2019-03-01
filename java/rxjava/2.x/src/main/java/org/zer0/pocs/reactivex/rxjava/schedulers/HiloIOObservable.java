package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class HiloIOObservable implements Runnable{

	@Override
	public void run() {
		Observable<String> observable = 
				Observable.<String>just("este","es","un","mensaje","en","varias","cadenas.");
		observable.
		doOnNext(t->System.out.println("Thead name:"+Thread.currentThread().getName())).
		subscribeOn(Schedulers.io()).
		subscribe(System.out::println);
		
	}

}
