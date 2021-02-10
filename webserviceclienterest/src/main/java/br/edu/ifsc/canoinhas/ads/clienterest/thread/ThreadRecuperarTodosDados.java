package br.edu.ifsc.canoinhas.ads.clienterest.thread;

import br.edu.ifsc.canoinhas.ads.clienterest.controller.ControllerClient2;

public class ThreadRecuperarTodosDados implements Runnable {

	private ControllerClient2 controllerClient2;

	public ThreadRecuperarTodosDados(ControllerClient2 controllerClient2) {
		this.controllerClient2 = controllerClient2;
	}

	public void run() {
		try {
			Thread.sleep(300);
			controllerClient2.recuperarUltimoDado();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
