package org.zer0.pocs.reactivex.rxjava.operadores;

import org.zer0.pocs.reactivex.rxjava.ConsumidorEjemplo8;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class TestGroupBy {

	private static final Integer[] numeros= {1,2,3,4,5,6,7,3,9,10,11,12,13,14,15,16,10,18,19,20};
	
	private void ejemplo_GroupBy1() {
		Observable<Integer> observable=Observable.fromArray(numeros);
		Observable<GroupedObservable<String, Integer>> o=observable.groupBy(
															n->{return n%2==0?"PAR":"IMPAR";}
														);
		GroupedObservable<String, Integer> p=o.blockingFirst();
		p.subscribe(System.out::println);
	}
	
	private void ejemplo_GroupBy2() {
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
	
	public static void main(String[] args) {
		TestGroupBy t =new TestGroupBy();
		//t.ejemplo_GroupBy();
	}
	
}
