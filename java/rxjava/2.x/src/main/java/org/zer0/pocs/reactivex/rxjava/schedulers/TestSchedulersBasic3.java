package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulersBasic3 {

	// En este caso no es necesario hacer uso del Thread.sleep dado que el trampoline usa el
	// hilo main.
	public static void main(String[] args)  throws Exception{
		Consumer<Integer> onNext=(v)->{
			System.out.println(Thread.currentThread().getName());
			System.out.println(v);
		};
			
		Observable.just(2, 4, 6, 8, 10)
		    .subscribeOn(Schedulers.trampoline())
		    .subscribe(onNext);
		
		Observable.just(1, 3, 5, 7, 9)
		    .subscribeOn(Schedulers.trampoline())
		    .subscribe(onNext);
		
	}
	
}
