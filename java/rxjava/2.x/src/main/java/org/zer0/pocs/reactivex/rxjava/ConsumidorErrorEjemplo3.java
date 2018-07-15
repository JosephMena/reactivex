package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.functions.Consumer;

public class ConsumidorErrorEjemplo3 implements Consumer<Throwable>{

	@Override
	public void accept(Throwable t) throws Exception {
		t.printStackTrace();
	}

}
