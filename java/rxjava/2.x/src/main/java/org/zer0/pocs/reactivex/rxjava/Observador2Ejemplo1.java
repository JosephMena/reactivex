package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Observador2Ejemplo1 implements Observer<String>{

	@Override
	public void onComplete() {
		System.out.println("Observador2Ejemplo1 complete...");
	}

	@Override
	public void onError(Throwable arg0) {
		System.out.println("mensaje 2:"+arg0.getMessage());
	}

	@Override
	public void onNext(String dato) {
		System.out.println("Observador2Ejemplo1 operando sobre:"+dato);
	}

	@Override
	public void onSubscribe(Disposable arg0) {
		System.out.println("Observador2Ejemplo1 suscrito");
	}
	
	

}
