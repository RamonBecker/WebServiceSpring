package br.edu.ifsc.canoinhas.ads.clienterest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RecuperarDataHora {
	public static void main(String[] args) {
//		LocalDateTime agora = LocalDateTime.now();
//		System.out.println(agora);
//		
//		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
//		String dataFormatada = formatterData.format(agora);
//		
//		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
//		String horaFormatada = formatterHora.format(agora);
//		
//		System.out.println("Data:"+dataFormatada);
//		System.out.println("Hora:"+horaFormatada);
//		
//		
		Random gerador = new Random();
		
		System.out.println("Temperatura positiva:"+gerador.nextInt(100));
		System.out.println("Temperatura negativa:"+gerador.nextInt(100) *-1);
		System.out.println("Umidade:"+gerador.nextInt(100));

		// TesteDado testeDado = new TesteDado();
		// testeDado.run();

	}
}
