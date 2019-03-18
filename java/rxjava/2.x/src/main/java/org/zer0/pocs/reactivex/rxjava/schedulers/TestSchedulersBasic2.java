package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulersBasic2 {

	private static Integer[] parItems = {
			2, 4, 6, 8, 10,12,14,16,18,20,
			22, 24, 26, 28, 30,32,34,36,38,40,
			42, 44, 46, 48, 50,52,54,56,58,60,
			62, 64, 66, 68, 70,72,74,76,78,80
		};
	
	private static Integer[] imparItems = {
			3, 5, 7, 9, 11,13,15,17,19,21,
			23, 25, 27, 29, 31,33,35,37,39,41,
			43, 45, 47, 49, 51,53,55,57,59,61,
			63, 65, 67, 69, 71,73,75,77,79,81
		};
	
	// En este ejemplo se puede apreciar que los dos observers hacen uso de un solo hilo
	// Primero se ejecuta el Observer de pares y luego el Observer de impares.
	public static void main(String[] args) throws Exception {
		Consumer<Integer> onNext=System.out::println;
			
		Observable.fromArray(parItems)
		    .subscribeOn(Schedulers.single())
		    .subscribe(onNext);
		
		System.out.println("emision!");
		
		Observable.fromArray(imparItems)
		    .subscribeOn(Schedulers.single())
		    .subscribe(onNext);
		Thread.sleep(5000);
	}
	
}
