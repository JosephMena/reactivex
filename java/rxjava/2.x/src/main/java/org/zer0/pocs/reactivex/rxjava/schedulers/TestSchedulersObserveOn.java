package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulersObserveOn {

	private Integer[] items = {
		2, 4, 6, 8, 10,12,14,16,18,20,
		22, 24, 26, 28, 30, 32, 34, 36, 38, 40,
		42, 44, 46, 48, 50, 52, 54, 56, 58, 60,
		62, 64, 66, 68, 70, 72, 74, 76, 78, 80,
		82, 84, 86, 88, 90, 92, 94, 96, 98, 100,
		102, 104, 106, 108, 110, 112
	};
	
	public static void main(String[] args)  throws Exception{
		TestSchedulersObserveOn t=new TestSchedulersObserveOn();
		t.test();
	}
	
	// En este ejemplo se puede apreciar algo importante de como trabaja rxjava y los schedulers
	// Aqui se probara el uso de dos Observable conectados por flatMap.
	//
	// 
	
	private void test() throws Exception {
		
		Consumer<Integer> onNext=(v)->{
			System.out.println("[onNext:"+Thread.currentThread().getName()+"] v:"+v);
		};
			
		Observable.fromArray(items)
			.subscribeOn(Schedulers.io())
			.map(
					(v)->{
						v=v+1;
						System.out.println("[map    :"+Thread.currentThread().getName()+"]"+v);
						return v;
					}
				)
			.observeOn(Schedulers.single())
			.filter(
					(v)->{
						System.out.println("[filter :"+Thread.currentThread().getName()+"]"+v);
						return v>50;
					}
				)
			
		    .flatMap(v->consumirRecursoExterno(v))
		    
		    .subscribe(onNext);
		Thread.sleep(10000);
	}
	
	private Observable<Integer> consumirRecursoExterno(Integer dato) {
		return Observable.just(dato)
				.map(
						(v)->{
							System.out.println("[Procesando dato:"+Thread.currentThread().getName()
									+"]:"+dato);
							return v;
						}
					)
				.subscribeOn(Schedulers.trampoline());
	}
	
}
