package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.Completable;

public class TestCompletable {

	// Completabe no emite items, sino solo una senial de que se completo una actividad o error, 
	// Notas:
	// 1. En este ejemplo a diferencia del TestBasic, no se necesita invocar al subscribe
	// para la ejecucion de los metodos se de, pero si para que el flujo se de correctamente.
	// 2. Como se puede apreciar aunque la invocacion del metodo1 de un Completable.error
	// se llega a invocar al metodo2.
	public static void main(String[] args) {
		TestCompletable t = new TestCompletable();
		t.testCompetable();
	}
	
	private void testCompetable() {
		Completable c = create();
		c.andThen(metodo1())
		.andThen(metodo2())
		.subscribe();
	}
	
	private Completable create() {
		System.out.println("creacion");
		return Completable.complete();
	}
	
	private Completable metodo1() {
		System.out.println("invocacion metodo 1");
		return Completable.error(new Exception("error metodo 1"));
	}
	
	private Completable metodo2() {
		System.out.println("invocacion metodo 2");
		return Completable.complete();
	}
	
}
