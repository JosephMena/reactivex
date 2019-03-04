package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulersBasic5 {

	private Integer[] items = {
		2, 4, 6, 8, 10,12,14,16
	};
	
	public static void main(String[] args)  throws Exception{
		TestSchedulersBasic5 t=new TestSchedulersBasic5();
		t.test();
	}
	
	
	private void test() throws Exception {
		
		Consumer<Integer> onNext=(v)->{
			System.out.println("======[onNext:"+Thread.currentThread().getName()+"] v:"+v);
		};
			
		Observable o = Observable.fromArray(items)
			.subscribeOn(Schedulers.io())
			.map(
					(v)->{
						v=v+1;
						System.out.println("[map:"+Thread.currentThread().getName()+"]"+v);
						return v;
					}
				);
		o.observeOn(Schedulers.single()).subscribe(onNext);
		
		o.subscribe(onNext);
			
		Thread.sleep(10000);
	}
	
	
	
}
