package org.zer0.pocs.reactivex.rxjava.operadores;

import java.util.Random;
import java.util.concurrent.Callable;

import org.zer0.pocs.reactivex.rxjava.ConsumidorEjemplo8;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.observables.GroupedObservable;

public class TestOperaciones {

	private static final String[] eventos= {"event 1","event 2","event 3","event 4","event 5","event 6","event 7","event 8",
			"event 9","event 10","event 11","event 12","event 13","event 14","event 15","event 16"};
	
	private static final Integer[] numeros= {1,2,3,4,5,6,7,3,9,10,11,12,13,14,15,16,10,18,19,20};
	
	private void ejemplo_Map() {
		Observable<String> observable=Observable.fromArray(eventos);
		Observable<String> datosConvertidos=observable.map(evento->evento.toUpperCase());
		datosConvertidos.subscribe(System.out::println);
	}
	
	private void ejemplo_Scan() {
		Observable<String> observable=Observable.fromArray(eventos);
		Observable<String> o=observable.scan((a,b)->{return a+b;});
		o.subscribe(System.out::println);
	}
	
	private void ejemplo_flatMap() {
		Observable<String> observable=Observable.fromArray(eventos);
		observable.flatMap(v-> Observable.just(v))
			.subscribe(System.out::println);
	}
	
	private void ejemplo_defaultIfEmpty() {
		Observable<String> observable=Observable.empty();
		Observable<String> o=observable.defaultIfEmpty("no hay datos!");
		o.subscribe(System.out::println);
		
		Observable<String> observable2=Observable.just("").defaultIfEmpty("Sin datos");
		observable2.subscribe(System.out::println);
		
		//Lanza excepcion de item null 
		Observable<String> observable3=Observable.just((String)null).defaultIfEmpty("Sin datos");
		observable2.subscribe(System.out::println);
	}
	
	private void ejemplo_first() {
		Observable<Integer> observable=Observable.fromArray(numeros);
		observable.first(3).subscribe(System.out::println);//En este caso 3 es el default si es q no se emite nada
	}
	
	//Devuelve un single<Boolean> si alguna de los elementos cumple o  no con el predicate pasado como
	//parametro
	private void ejemplo_any(){
		Observable<String> o=Observable.just("Joseph","Cesar");
		Single<Boolean> contienePeru=o.any(texto->texto.contains("Peru"));
		System.out.println(contienePeru.blockingGet());
	}
	
	//Convierte el o los items a otro tipo de valor.
	//Recive como parametro un ObservableConverter, el cual es una interface funcional 
	private void ejemplo_as() {
		Observable<String> o=Observable.just("9");
		Integer val=o.as((c)->{
			return Integer.parseInt(c.blockingFirst());
		});
		System.out.println("->"+val);
	}
	
	//Devuelve un Single<Boolean> si algunos de los items del ObservableSource es igual al parametro 
	//pasado
	private void ejemplo_contains() {
		Observable o=Observable.fromArray(numeros);
		Single<Boolean> contiene=o.contains(15);
		contiene.subscribe(System.out::println);
	}
	
	//Devuelve un Single<Long> con el numero de items generados por el ObservableSource
	private void ejemplo_count() {
		Observable o=Observable.fromArray(numeros);
		Single<Long> s=o.count();
		s.subscribe(System.out::println);
	}
	
	//Devuelve un Observable que no contiene elementos repetidos
	//Tambien se le puede pasar una Function como parametro y q no sea el equals el comparador.
	private void ejemplo_distinct() {
		Observable o=Observable.fromArray(numeros);
		Observable f=o.distinct();
		f.count().subscribe(System.out::println);
	}
	
	//Devuelve un Maybe con el elemento de la posicion X.
	//Tambien existe el metodo elementAt(index,default) el cual devuelve un Single
	private void ejemplo_elementAt() {
		Observable o=Observable.fromArray(numeros);
		Maybe<Integer> f=o.elementAt(25);
		f.subscribe(System.out::println);
	}
	
	//Filetra aquellos elementos emitidos segun el Predicate que se le pasa como parametro
	private void ejemplo_filter() {
		Observable<Integer> o=Observable.fromArray(numeros);
		Observable<Integer> f=o.filter(v->{return v<10;});
		f.subscribe(System.out::println);
	}
	
	//Aplica una Consumer sobre los items del Observable.
	private void ejemplo_forEach() {
		Observable<Integer> o=Observable.fromArray(numeros);
		o.forEach(System.out::println);
		o.subscribe(System.out::println);
	}
	
	public static void main(String[] args) {
		TestOperaciones t=new TestOperaciones();
		t.ejemplo_flatMap();
		//t.ejemplo_Map();
		//t.ejemplo_Scan();
		//t.ejemplo_defaultIfEmpty();
		//t.ejemplo_first();
		//t.ejemplo_any();
		//t.ejemplo_as();
		//t.ejemplo_count();
		//t.ejemplo_distinct();
		//t.ejemplo_elementAt();
		//t.ejemplo_filter();
		//t.ejemplo_forEach();
	}
	
}
