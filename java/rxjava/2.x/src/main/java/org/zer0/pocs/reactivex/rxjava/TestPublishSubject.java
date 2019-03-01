package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.subjects.PublishSubject;

public class TestPublishSubject {

	
	private void ejemplo_PublishSubject_1() {
		PublishSubject<String>	publishSubject=PublishSubject.create();
		publishSubject.subscribe(
					(param)->{
						System.out.println("Observer 1:"+param);
						}
					);
		publishSubject.onNext("Item 1");
		publishSubject.onNext("Item 2");
		publishSubject.onNext("Item 3");
		
		publishSubject.subscribe(
				(param)->{
					System.out.println("Observer 2:"+param);
					}
				);
		publishSubject.onNext("Item 4");
		publishSubject.onNext("Item 5");
	}
	
	public static void main(String[] args) {
		TestPublishSubject t=new TestPublishSubject();
		//t.ejemplo15_PublishSubject();
	}
}
