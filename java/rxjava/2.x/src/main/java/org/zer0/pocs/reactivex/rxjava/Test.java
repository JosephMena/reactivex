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
	
	
	/* ------------------------------ Operadores ------------------------------ */
	/* ------------------------------------------------------------------------ */
	
	private void ejemplo5_Map() {
		Observable<String> observable=Observable.fromArray(eventos);
		Observable<String> datosConvertidos=observable.map(evento->evento.toUpperCase());
		datosConvertidos.subscribe(System.out::println);
	}
	
	private void ejemplo6_Scan() {
		Observable<String> observable=Observable.fromArray(eventos);
		Observable<String> o=observable.scan((a,b)->{return a+b;});
		o.subscribe(System.out::println);
	}
	
	private void ejemplo7_GroupBy() {
		Observable<Integer> observable=Observable.fromArray(numeros);
		Observable<GroupedObservable<String, Integer>> o=observable.groupBy(
															n->{return n%2==0?"PAR":"IMPAR";}
														);
		GroupedObservable<String, Integer> p=o.blockingFirst();
		p.subscribe(System.out::println);
	}
	
	private void ejemplo8_GroupBy() {
		Integer sumaPares=0;
		Integer sumaImpares=0;
		Observable<Integer> observable=Observable.fromArray(numeros);
		Observable<GroupedObservable<String, Integer>> o=observable.groupBy(
															n->{return n%2==0?"PAR":"IMPAR";}
														);
		ConsumidorEjemplo8 c1=new ConsumidorEjemplo8("Par");
		ConsumidorEjemplo8 c2=new ConsumidorEjemplo8("Impar");
		
		o.subscribe(
					grupo->{
							grupo.subscribe(c1);
						}
					);
	}
	
	
	private void ejemplo9_defaultIfEmpty() {
		Observable<String> observable=Observable.empty();
		Observable<String> o=observable.defaultIfEmpty("no hay datos!");
		o.subscribe(System.out::println);
		
		Observable<String> observable2=Observable.just("").defaultIfEmpty("Sin datos");
		observable2.subscribe(System.out::println);
		
		//Lanza excepcion de item null 
		Observable<String> observable3=Observable.just((String)null).defaultIfEmpty("Sin datos");
		observable2.subscribe(System.out::println);
	}
	
	private void ejemplo10_first() {
		Observable<Integer> observable=Observable.fromArray(numeros);
		observable.first(3).subscribe(System.out::println);//En este caso 3 es el default si es q no se emite nada
		
	}
	
	/* ------------------------------ Connectable,Single------------------------ */
	/* ------------------------------------------------------------------------- */
	
	private void ejemplo11_Connectable() {
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
	
	private void ejemplo12_Connectable() {
		ConnectableObservable<String> connectableObserver=Observable.fromArray(eventos).publish();
		connectableObserver.subscribe(System.out::println);
		//El codigo desarrollado 3 lineas arriba no ejecutara nada hasta que se llame a connect.
		connectableObserver.connect();
	}
	
	private void ejemplo13_Single() {
		Single<String> single=Observable.just("Joseph Mena").singleOrError();
		single.subscribe(System.out::println);
	}
	
	private void ejemplo14_Single() {
		Single<String> single=Single.just("Un unico elemento.");
		single.subscribe(System.out::println);
	}
	//
	
	private void ejemplo15_PublishSubject() {
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
		t.ejemplo15_PublishSubject();
	}
}
