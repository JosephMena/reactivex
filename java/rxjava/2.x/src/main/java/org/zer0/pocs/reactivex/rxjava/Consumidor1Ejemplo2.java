package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.functions.Consumer;

public class Consumidor1Ejemplo2 implements Consumer<String>{

	@Override
	public void accept(String parametro) throws Exception {
		System.out.println("consumiendo "+parametro+" ....");
	}

}
