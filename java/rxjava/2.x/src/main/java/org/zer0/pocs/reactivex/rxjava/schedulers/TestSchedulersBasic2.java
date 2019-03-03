package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulersBasic2 {

	// En este ejemplo se puede apreciar que los dos observers hacen uso de un solo hilo
	public static void main(String[] args) throws Exception {
		Consumer<Integer> onNext=System.out::println;
			
		Observable.just(2, 4, 6, 8, 10)
		    .subscribeOn(Schedulers.single())
		    .subscribe(onNext);
		
		Observable.just(1, 3, 5, 7, 9)
		    .subscribeOn(Schedulers.single())
		    .subscribe(onNext);
		Thread.sleep(3000);
	}
	
}
