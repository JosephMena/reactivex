package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class TestFlowable {

	public static void main(String[] args) {
		TestFlowable tf = new TestFlowable();
		tf.testFirstOrError2();
		//tf.testFirstOrError();
	}
	
	//En este caso retorna un Maybe
	private void testFirstElement() {
		Flowable<String> f = Flowable.empty();
		Maybe<String> valor = f.firstElement();
		valor.doOnComplete(()->{System.out.println("sin elementos");})
		.subscribe(System.out::println);
	}
	
	// En este caso retorna un Single, pero si no hay elementos se lanza una excepcion
	// del tipo NoSuchElements
	private void testFirstOrError() {
		Flowable<String> f = Flowable.empty();
		Single<String> valor = f.firstOrError();
		valor.subscribe(System.out::println);
	}
	
	private void testFirstOrError2() {
		Flowable<String> f = Flowable.empty();
		Single<String> valor = f.firstOrError().map(
					r->{System.out.println("map after error");return r;}
				);
		valor.subscribe(System.out::println);
	}
	
}
