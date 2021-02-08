package br.edu.ifsc.canoinhas.ads.clienterest;

public class TesteDado implements Runnable {

	public void run() {

		while (true) {
			try {
				System.out.println("Ola");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
