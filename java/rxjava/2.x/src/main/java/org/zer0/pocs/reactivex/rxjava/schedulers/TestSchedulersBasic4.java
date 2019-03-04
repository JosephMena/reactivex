package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulersBasic4 {

	private Integer[] items = {
		2, 4, 6, 8, 10,12,14,16,18,20,
		22, 24, 26, 28, 30,32,34,36,38,40,
		42, 44, 46, 48, 50,52,54,56,58,60,
		62, 64, 66, 68, 70,72,74,76,78,80
	};
	
	public static void main(String[] args)  throws Exception{
		TestSchedulersBasic4 t=new TestSchedulersBasic4();
		t.test();
	}
	
	
	private void test() throws Exception {
		
		Consumer<Integer> onNext=(v)->{
			System.out.println("[onNext:"+Thread.currentThread().getName()+"] v:"+v);
		};
			
		Observable.fromArray(items)
			.subscribeOn(Schedulers.io())
			.map(
					(v)->{
						v=v+1;
						System.out.println("[map:"+Thread.currentThread().getName()+"]"+v);
						return v;
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
				.subscribeOn(Schedulers.single());
	}
	
}
