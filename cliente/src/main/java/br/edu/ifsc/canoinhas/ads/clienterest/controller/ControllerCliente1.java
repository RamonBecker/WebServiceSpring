package br.edu.ifsc.canoinhas.ads.clienterest.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ControllerCliente1 implements Runnable {

	private Random gerador;
	private int id;
	private String temperatura;
	private String umidade;
	private String luminosidade;
	private LocalDateTime localHoraData;
	private DateTimeFormatter formatadorData;
	private DateTimeFormatter formatadorHora;
	private String dataFormatada;
	private String horaFormatada;

	public ControllerCliente1() {
		this.gerador = new Random();
		this.formatadorData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		this.formatadorHora = DateTimeFormatter.ofPattern("HH:mm");
		this.id = 1;
	}

	public void run() {
		
		while(true) {
			try {
				post();
				Thread.sleep(10000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}

	private void post() {
		try {

			URL url = new URL("http://localhost:8080/dado/adiciona");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-type", "application/json");
			conn.setDoOutput(true);

			luminosidade = String.valueOf(gerador.nextInt(100));
			umidade = String.valueOf(gerador.nextInt(100));
			temperatura = String.valueOf(gerador.nextInt(100));

			this.localHoraData = LocalDateTime.now();

			this.dataFormatada = formatadorData.format(localHoraData);
			this.horaFormatada = formatadorHora.format(localHoraData);

			System.out.println("Temperatura:" + temperatura);
			System.out.println("Umidade:" + umidade);
			System.out.println("Luminosidade:" + luminosidade);
			System.out.println("Data:" + dataFormatada);
			System.out.println("Hora:" + horaFormatada);
			System.out.println("ID:" + id);

			String aux_id = String.valueOf(id);
			// String jsonInputString = "{\"id\":\"4\", \"nome\":\"teste\",
			// \"idade\":\"4\"}";

			String jsonInputString = "{\"id\":\"" + aux_id + "\", \"data\":\"" + dataFormatada + "\", \"hora\":\""
					+ horaFormatada + "\", \"temperatura\":\"" + temperatura + "\", \"umidade\":\"" + umidade
					+ "\", \"luminosidade\":\"" + luminosidade + "\"}";
			this.id++;

			try {
				OutputStream os = conn.getOutputStream();
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
