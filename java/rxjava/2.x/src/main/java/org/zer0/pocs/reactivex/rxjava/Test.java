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

public class Test {

	private static final String[] eventos= {"event 1","event 2","event 3","event 4","event 5","event 6","event 7","event 8",
			"event 9","event 10","event 11","event 12","event 13","event 14","event 15","event 16"};
	
	private static final Integer[] numeros= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	
	private void basico1(){
		Observable<String> o1=Observable.just("Hola");//Hasta un maximo de 9 parametros
		
		Observable<String> o2=Observable.fromArray(eventos);
	}
	
	private void basico2(){
		Observable<String> o1=Observable.just("Hola");//Hasta un maximo de 9 parametros
		o1.doOnSubscribe((d)->{System.out.println("suscrito");})
		  .subscribe(System.out::println);
	}
	
	private void basico3(){
		
		
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
	
	private void ejemplo4_Lambda(){
		Observable<String> observable=Observable.fromArray(eventos);
		observable.subscribe(
								datoConsumir->System.out.println(datoConsumir)
								,error->error.printStackTrace()
								,()->System.out.println("completado")
							);
	}
	
	
	/* ------------------------------ Connectable,Single------------------------ */
	/* ------------------------------------------------------------------------- */
	
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
	
	private void ejemplo_Single_1() {
		Single<String> single=Observable.just("Joseph Mena").singleOrError();
		single.subscribe(System.out::println);
	}
	
	private void ejemplo_Single_2() {
		Single<String> single=Single.just("Un unico elemento.");
		single.subscribe(System.out::println);
	}
	//
	
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
	
//	private void ejemplo15_Using() {
//		Single<String> single=Observable.usin
//		single.subscribe(System.out::println);
//	}
	
	
	public static void main(String[] args) {
		Test t=new Test();
		//t.basico2();
		//t.ejemplo1_Observable_Observer();
		//t.ejemplo2_Observable_Consumidor();
		//t.ejemplo3_Observable_Consumidor();
		//t.ejemplo4_Lambda();
		//t.ejemplo5_Map();
		//t.ejemplo6_Scan();
		//t.ejemplo8_GroupBy();
		//t.ejemplo9_defaultIfEmpty();
		//t.ejemplo10_first();
		//t.ejemplo11_Connectable();
		//t.ejemplo12_Connectable();
		//t.ejemplo13_Single();
		//t.ejemplo15_PublishSubject();
	}
}
