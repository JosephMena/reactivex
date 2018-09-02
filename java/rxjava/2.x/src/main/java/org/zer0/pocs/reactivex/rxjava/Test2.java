package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.Observable;

public class Test2 {

	
	public static void main(String[] a) {
		Test2 t=new Test2();
		//t.pruebaEmisionDeItems();
		t.pruebaEmisionDeItemsCache();
	}
	
	//ObservableOnSubscribe
	private void pruebaEmisionDeItems() {
		Observable<String> observable=Observable.create(emitter->{
								    Thread thread = new Thread(() -> {
								        try {
								        	while(true) {
								        		System.out.println("en el while!");
								        		String valorEmsion="Desde un servicio!!!";//Se podria invocar a un servicio externo
									            emitter.onNext(valorEmsion);
								        	}
								            
								        } catch (Exception e) {
								            emitter.onError(e);
								        }
								    });
								    thread.start();
							 });
		observable.subscribe(System.out::println);
	}
	
	//El Observable sin cachear ejecutara el metodo create las veces que alguien se subscriba a el.
	//En cambio, el metodo cache solo invoca al metodo create una vez y luego el resultado es cacheado
	//para enviarselos a los subscriptores.
	private void pruebaEmisionDeItemsCache() {
		Observable<String> observable=Observable.create(emitter->{
						        		System.out.println("en el while!");
						        		String valorEmsion="Desde un servicio!!!";//Se podria invocar a un servicio externo
							            emitter.onNext(valorEmsion);
							            emitter.onComplete();
								       
							 		});
		observable.subscribe(System.out::println);
		observable.subscribe(System.out::println);
		observable.subscribe(System.out::println);
		Observable<String> oCache=observable.cache();
		oCache.subscribe(System.out::println);
		oCache.subscribe(System.out::println);
		oCache.subscribe(System.out::println);
	}
	
	
}
