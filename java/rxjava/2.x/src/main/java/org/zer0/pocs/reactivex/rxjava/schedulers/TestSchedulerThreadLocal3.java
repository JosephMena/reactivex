package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulerThreadLocal3 {

	private ThreadLocal<String> dni = new ThreadLocal<>(); 
	
	public static void main(String[] args) throws Exception {
		TestSchedulerThreadLocal3 test = new TestSchedulerThreadLocal3();
		test.testThreadLocal();
		Thread.sleep(3000);
	}
	
	private void testThreadLocal() {
		realizarOperacion().subscribe(
				(r)->{
					System.out.println(r.mensaje);
				});
	}
	
	private Single<Resultado> realizarOperacion() {
		return callServicio()
		.andThen(saveRespuesta())
		.andThen(
				makeResultado(dni.get())
				);
	}
	
	private Completable saveRespuesta() {
		dni.set("1111");
		return Completable.complete();
	}
	
	private Completable callServicio(){
		return Completable.complete();
	}
	
	private Single<Resultado> makeResultado(String respuestaServicio){
		return Single.fromCallable(
					()->new Resultado(respuestaServicio)
		);
	}
	
	private class Resultado{
		private String mensaje;

		public Resultado(String mensaje) {
			this.mensaje = mensaje;
		}

		public String getMensaje() {
			return mensaje;
		}

		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		
	}
	
	private class RespuestaServicio{
		private String respuesta;

		public RespuestaServicio(String respuesta) {
			this.respuesta = respuesta;
		}

		public String getRespuesta() {
			return respuesta;
		}

		public void setRespuesta(String respuesta) {
			this.respuesta = respuesta;
		}
	}
}
