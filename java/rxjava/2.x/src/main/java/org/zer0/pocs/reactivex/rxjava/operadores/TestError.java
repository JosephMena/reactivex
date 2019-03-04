package org.zer0.pocs.reactivex.rxjava.operadores;

import io.reactivex.Observable;

public class TestError {

	private static final Integer[] numeros= {1,2,3,4,5,6,7,3,9,10,11,12,13,14,15,16,10,18,19,20};
	
	private void ejemploError() {
		error();
	}
	
	private Observable error() {
		Observable<Integer> o=Observable.fromArray(numeros);
		o.forEach(System.out::println);
		o.switchIfEmpty(Observable.error(new Exception("error!!!!!!!!")));
		o.forEach(System.out::println);
		System.out.println("sal 2");
		return o;
	}
	
	private Observable error2() {
		Observable<Integer> o=Observable.fromArray(numeros);
		o.forEach(System.out::println);
		
		System.out.println("sal 3");
		return o;
	}
	
	public static void main(String[] args) {
		TestError t=new TestError();
		//t.error2();
	}
}
