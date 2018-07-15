package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
public class Test {

	private static final String[] eventos= {"event 1","event 2","event 3","event 4","event 5","event 6","event 7","event 8",
			"event 9","event 10","event 11","event 12","event 13","event 14","event 15","event 16"};
	
	public static void main(String[] args) {
		Test t=new Test();
		//t.ejemplo1_Observable_Observer();
		//t.ejemplo2_Observable_Consumidor();
		//t.ejemplo3_Observable_Consumidor();
		t.ejemplo4_Lambda();
	}
	
	private void basico1(){
		Observable<String> o1=Observable.just("Hola");//Hasta un maximo de 9 parametros
		
		Observable<String> o2=Observable.fromArray(eventos);
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
	
	private void ejemplo5_Map() {
		Observable<String> observable=Observable.fromArray(eventos);
		Observable<String> datosConvertidos=observable.map(evento->evento.toUpperCase());
		datosConvertidos.subscribe(System.out::println);
	}
	
	
	
	
	
}
