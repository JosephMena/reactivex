package org.zer0.pocs.reactivex.rxjava.schedulers;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class TestSchedulerThreadLocal2 {

	private final ThreadLocal<String> dni = new ThreadLocal<>(); 
	
	public static void main(String[] args) throws Exception {
		TestSchedulerThreadLocal2 test = new TestSchedulerThreadLocal2();
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
		.flatMap(rs->
				{
					return saveRespuesta(rs)
					.andThen(makeResultado(dni.get()));
				}
		);
	}
	
	private Completable saveRespuesta(RespuestaServicio respuesta) {
		dni.set(respuesta.getRespuesta());
		return Completable.complete();
	}
	
	private Single<RespuestaServicio> callServicio(){
		return Single.fromCallable(
				()->new RespuestaServicio("1111")
			);
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
