package br.edu.ifsc.canoinhas.ads.clienterest.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifsc.canoinhas.ads.clienterest.entidade.Dado;

public class ControllerClient2 {
	private Map<Integer, Dado> dadosMap = new HashMap<Integer, Dado>();

	public void recuperarUltimoDado() {

		try {

			URL url = new URL("http://localhost:8080/dado/ultimo");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Recuperando a última leitura .... \n");
			while ((output = br.readLine()) != null) {
			//	System.out.println(output);
				formatarString(output, "");
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public void recuperarPorIDDado(String id) {
		try {

			URL url = new URL("http://localhost:8080/dado/buscaid/" + id);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Buscando dado por id...");
			while ((output = br.readLine()) != null) {
				formatarString(output, "id");
			}
	
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	
	public void recuperarPorHora(String hora) {
		try {

			URL url = new URL("http://localhost:8080/dado/buscahora/" + hora);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Buscando dado por id...");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				//formatarString(output, "id");
			}
	
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
	
	private void formatarString(String json, String tipo) {
		String[] array = json.split(",");

		List<String> chaves = new ArrayList<String>();
		chaves.add("id");
		chaves.add("data");
		chaves.add("hora");
		chaves.add("temperatura");
		chaves.add("umidade");

		List<String> valores = new ArrayList<String>();

		for (int i = 0; i < array.length; i++) {
			for (int k = 0; k < chaves.size(); k++) {
				String chave = chaves.get(k);
				if (array[i].contains(chave)) {
					valores.add(array[i]);

				}

			}

		}

		String[] novo_valor = null;

		List<String> novos_valores = new ArrayList<String>();

		String aux_umidade = "";

		String[] valor_umidade = null;
		for (String valor : valores) {
			if (valor.contains("umidade")) {
				valor_umidade = valor.split(":");
				for (int i = 0; i < valor_umidade.length; i++) {
					String umidade = valor_umidade[i];
					if (i == 1) {
						if (!aux_umidade.contains(umidade)) {
							aux_umidade += umidade;
							novos_valores.add(aux_umidade);
						}
					}

				}

			} else {

				novo_valor = valor.split(":");
				for (int i = 0; i < novo_valor.length; i++) {
					String aux_valor = novo_valor[i];
					if (i == 1) {
						if (novo_valor[i - 1].contains("hora")) {
							novos_valores.add(aux_valor);
							novos_valores.add(novo_valor[i + 1]);
						} else {
							novos_valores.add(aux_valor);
						}
					}
				}
			}
		}

		String[] valor_sem_formatacao = null;

		List<String> valores_sem_formatacao = new ArrayList<String>();

		for (String valor : novos_valores) {
			valor_sem_formatacao = valor.split("\"");

			for (int i = 0; i < valor_sem_formatacao.length; i++) {
				if (!valor_sem_formatacao[i].isEmpty()) {
					if (!valor_sem_formatacao[i].contains("}")) {
						valores_sem_formatacao.add(valor_sem_formatacao[i]);
					}
				}
			}
		}
		String aux_hora = valores_sem_formatacao.get(2) + ":" + valores_sem_formatacao.get(3);

		if (tipo.contains("id")) {
			//System.out.println("Dado recuperado:"+recuperarId(valores_sem_formatacao.get(0)));
			Dado dado = new Dado(Integer.parseInt(valores_sem_formatacao.get(0)), valores_sem_formatacao.get(1),
					aux_hora, valores_sem_formatacao.get(4), valores_sem_formatacao.get(5),
					valores_sem_formatacao.get(6));
			System.out.println("Dado recuperado: "+dado);
		} else {

			Dado dado = new Dado(Integer.parseInt(valores_sem_formatacao.get(0)), valores_sem_formatacao.get(1),
					aux_hora, valores_sem_formatacao.get(4), valores_sem_formatacao.get(5),
					valores_sem_formatacao.get(6));
			dadosMap.put(dado.getId(), dado);
		}
	}

	private Dado recuperarId(String id) {
		Integer aux_id = Integer.parseInt(id);
		if (getDadosMap().containsKey(aux_id)) {
			return getDadosMap().get(aux_id);
		} else {
			return null;
		}

	}

	public Map<Integer, Dado> getDadosMap() {
		if (dadosMap == null) {
			dadosMap = new HashMap<Integer, Dado>();
		}
		return dadosMap;
	}

	public Dado recuperarUltimoDadoLeitura() {
		return getDadosMap().get(dadosMap.size() - 1);

	}
}
