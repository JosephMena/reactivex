package org.zer0.pocs.reactivex.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.subjects.PublishSubject;

public class TestConnectable {

	private static final String[] eventos= {"event 1","event 2","event 3","event 4","event 5","event 6","event 7","event 8",
			"event 9","event 10","event 11","event 12","event 13","event 14","event 15","event 16"};
	
	private void ejemplo_Connectable_1() {
		ConnectableObservable<Long> connectableObserver=
									Observable.interval(200, TimeUnit.MILLISECONDS).publish();
		connectableObserver.subscribe(System.out::println);
		//El codigo desarrollado 3 lineas arriba no ejecutara nada hasta que se llame a connect.
		connectableObserver.connect();
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void ejemplo_Connectable_2() {
		ConnectableObservable<String> connectableObserver=Observable.fromArray(eventos).publish();
		connectableObserver.subscribe(System.out::println);
		//El codigo desarrollado 3 lineas arriba no ejecutara nada hasta que se llame a connect.
		connectableObserver.connect();
	}
	
	public static void main(String[] args) {
		TestConnectable t=new TestConnectable();
		//t.ejemplo11_Connectable();
		//t.ejemplo12_Connectable();
	}
}
