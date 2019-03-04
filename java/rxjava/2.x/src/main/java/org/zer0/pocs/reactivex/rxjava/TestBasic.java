package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.Single;

public class TestBasic {

	// Si este algoritmo se deja tal cual sin el subscribe de Observers entonces
	// no se llega a realizar las invocaciones a los metodos que hacen los System.out.println
	public static void main(String[] args) {
		TestBasic t = new TestBasic();
		t.creacion()
		.map(t::metodo1)
		.map(t::metodo2);
	}
	
	private Single<String> creacion() {
		return Single.just("Hola mundo!");
	}
	
	private String metodo1(String texto) {
		System.out.println("invocacion metodo1");
		return texto + " este es un pais con mucha riqueza"; 
	}
	
	private String metodo2(String texto) {
		System.out.println("invocacion metodo2");
		return texto + " pero corrupto, algun dia saldremos?"; 
	}
}
