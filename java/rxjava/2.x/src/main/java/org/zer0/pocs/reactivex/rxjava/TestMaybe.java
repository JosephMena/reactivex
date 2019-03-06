package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.Maybe;

public class TestMaybe {

	
	public static void main(String[] args) {
		
	}
	
	private void test1() {
		Maybe<String> dato = Maybe.just("Joseph");
		dato.isEmpty()
		.map(empty->{
			if(empty)
				return "Joseph";
			else
				return 12;
		})
		;
	}
	
	private void testSwitchEmpty() {
		Maybe<String> dato = Maybe.empty();
		dato.switchIfEmpty(callMaybeSource());
		
	}
	
	private Maybe<String> callMaybeSource() {
		return Maybe.just("saludos!");
	}
	
	private void test2() {
		
//		Maybe<Peticion> dato = Maybe.just(new Peticion(null));
//		dato.filter(p->p.resultado==null)
//		.switchIfEmpty()
//	
//		;
	}
	
	
	
	private class Peticion{
		String resultado;

		public Peticion(String resultado) {
			this.resultado = resultado;
		}
	}
}
