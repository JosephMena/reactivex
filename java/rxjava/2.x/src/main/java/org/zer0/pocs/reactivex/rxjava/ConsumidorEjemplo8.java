package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.functions.Consumer;

public class ConsumidorEjemplo8 implements Consumer<Integer>{

	private String nombre;

	@Override
	public void accept(Integer t) throws Exception {
		System.out.println(nombre+": "+t);
		
	}

	public ConsumidorEjemplo8(String nombre) {
		this.nombre = nombre;
	}
	
	
}
