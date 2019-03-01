package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.Observable;

public class TestOtros {

	private static final String[] eventos= {"event 1","event 2","event 3","event 4","event 5","event 6","event 7","event 8",
			"event 9","event 10","event 11","event 12","event 13","event 14","event 15","event 16"};
	
	private void ejemplo4_Lambda(){
		Observable<String> observable=Observable.fromArray(eventos);
		observable.subscribe(
								datoConsumir->System.out.println(datoConsumir)
								,error->error.printStackTrace()
								,()->System.out.println("completado")
							);
	}
	
	public static void main(String[] args) {
		TestOtros t=new TestOtros();
		t.ejemplo4_Lambda();
	}
	
}
