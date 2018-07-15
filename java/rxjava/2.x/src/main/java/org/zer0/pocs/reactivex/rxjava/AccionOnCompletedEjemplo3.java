package org.zer0.pocs.reactivex.rxjava;

import io.reactivex.functions.Action;

public class AccionOnCompletedEjemplo3 implements Action{

	@Override
	public void run() throws Exception {
		System.out.println("AccionOnCompletedEjemplo3 completado..");
	}

}
