package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulersBasic {

	public static void main(String[] args) throws Exception {
		TestSchedulersBasic t=new TestSchedulersBasic();
		t.testObservableSingleTheadSleep();
	}
	
	// En este ejemplo se puede apreciar como el emitter y el observer corren sobre el mismo
	// hilo, en este caso el hilo main.
	// Al ser de esa forma(que corre sobre el hilo main), el observer(para este caso el 
	// consumidor) y el println "hola mundo" son ejecutados secuencialmente.
	private void testObservableDefault() {
		Observable.just(1,3,5,7,9)
			.doOnNext((c)->{System.out.println("Hilo:"+Thread.currentThread().getName());})
			.subscribe(System.out::println);
		System.out.println("hola mundo");
	}
	
	// Si se ejecuta este metodo, nos daremos cuenta que solo se pinta la palabra "hola mundo"
	// esto se debe a que el hilo main termina antes de que el hilo del Observable se llege
	// a ejecutar, ejecutar testObservableSingleTheadSleep para ver la ejecucion del Obsrevable. 
	private void testObservableSingleThead() {
		Observable.just(1,3,5,7,9)
			.doOnNext((c)->{System.out.println("Hilo:"+Thread.currentThread().getName());})
			.subscribeOn(Schedulers.single())
			.subscribe(System.out::println);
		System.out.println("hola mundo");
	}
	
	private void testObservableSingleTheadSleep() throws Exception {
		Observable.just(1,3,5,7,9)
			.doOnNext((c)->{System.out.println("Hilo:"+Thread.currentThread().getName());})
			.subscribeOn(Schedulers.single())
			.subscribe(System.out::println);
		System.out.println("hola mundo");
		Thread.sleep(3000);
	}
	
}
