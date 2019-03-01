package org.zer0.pocs.reactivex.rxjava;

import java.util.Random;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

public class TestInstanciacion {

	
	private static final String[] eventos= {"event 1","event 2","event 3","event 4","event 5","event 6","event 7","event 8",
			"event 9","event 10","event 11","event 12","event 13","event 14","event 15","event 16"};
	
	
	private void basico1() {
		Observable<String> o1 = Observable.just("Hola");// Hasta un maximo de 9 parametros

		Observable<String> o2 = Observable.fromArray(eventos);
	}

	private void basico2() {
		Observable<String> o1 = Observable.just("Hola");
		o1.doOnSubscribe((d) -> {
			System.out.println("suscrito");
		}).subscribe(System.out::println);
	}
	
	private void ejemplo1_Observable_Observer() {
		Observable<String> observable=Observable.just(
				"Hola Mundo","Hola Peru","Hola Lima","Hola Callao","Hola Bellavista",
				"Hola Ventanilla","Hola La Punta","Hola Chucuito","Hola La Perla","Hola Callao cercado");
		
		Observer<String> observer1=new Observador1Ejemplo1();
		Observer<String> observer2=new Observador2Ejemplo1();
		
		observable.subscribe(observer1);
		observable.subscribe(observer2);
	}
	
	private void ejemploN_Observable_Observer() {
		Observable<String> observable=Observable.error(new Exception("error general"));
		
		Observer<String> observer1=new Observador1Ejemplo1();
		Observer<String> observer2=new Observador2Ejemplo1();
		
		observable.subscribe(observer1);
		observable.subscribe(observer2);
	}
	
	private void ejemplo2_Observable_Consumidor() {
		Observable<String> observable=Observable.just("Más Minerales!");
		
		Consumer<String> consumidor=new Consumidor1Ejemplo2();
		
		observable.subscribe(consumidor);
	}
	
	private void ejemplo3_Observable_Consumidor() {
		Observable<String> observable=Observable.just("Más Minerales!");
		
		Consumer<String> consumidor=new Consumidor1Ejemplo2();
		Consumer<Throwable> consumidorError=new ConsumidorErrorEjemplo3();
		AccionOnCompletedEjemplo3 accion=new AccionOnCompletedEjemplo3();
		
		observable.subscribe(consumidor,consumidorError,accion);
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
		
		Observable<String> o=observable.cache();
		observable.subscribe(System.out::println);
		observable.subscribe(System.out::println);
		observable.subscribe(System.out::println);
		
		Observable<String> oCache=observable.cache();
		oCache.subscribe(System.out::println);
		oCache.subscribe(System.out::println);
		oCache.subscribe(System.out::println);
	}
	
	private Callable<Integer> generador(){
		Callable<Integer> c=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				Random generator = new Random();
		        Integer randomNumber = generator.nextInt(5);
		        return randomNumber;
			}
		};
		return c;
	}
	
	private void ejemplo_fromCallable() {
		Observable<Integer> o=Observable.fromCallable(generador());
		o.subscribe(System.out::println);
	}
	
	private void ejemplo_Single_1() {
		Single<String> single=Observable.just("Joseph Mena").singleOrError();
		single.subscribe(System.out::println);
	}
	
	private void ejemplo_Single_2() {
		Single<String> single=Single.just("Un unico elemento.");
		single.subscribe(System.out::println);
	}
	
	public static void main(String[] a) {
		TestInstanciacion t=new TestInstanciacion();
		//t.pruebaEmisionDeItems();
		//t.ejemplo_fromCallable();
		////t.basico2();
		t.ejemploN_Observable_Observer();
	}
	
}
