package br.edu.ifsc.canoinhas.dado.controller;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifsc.canoinhas.dado.entidade.Dado;

public class DadoDB {

	public static DadoDB instance;
	private Map<Integer, Dado> dadosMap = new HashMap<Integer, Dado>();

	public static synchronized DadoDB getInstance() {
		if (instance == null)
			instance = new DadoDB();

		return instance;
	}

	public synchronized void adicionaDado(Integer id, Dado d) {
		dadosMap.put(id, d);
	}

	public synchronized Dado buscaDado(Integer id) {
		return dadosMap.get(id);
	}

	public synchronized void atualizaDado(Integer id, Dado d) {
		dadosMap.put(id, d);
	}

	public synchronized void deletarDado(Integer id) {
		dadosMap.remove(id);
	}

	public synchronized Dado recuperarUltimoDado() {
		return dadosMap.get(dadosMap.size() - 1);
	}

	public synchronized HashMap<Integer, Dado> recuperarDadoHora(String hora) {
		HashMap<Integer, Dado> listaHora = new HashMap<Integer, Dado>();

		for (Integer id : dadosMap.keySet()) {
			Dado dado = dadosMap.get(id);
			if (dado.getHora().contains(hora)) {
				listaHora.put(dado.getId(), dado);
			}
		}
		return listaHora;
	}

	public Map<Integer, Dado> getDadosMap() {
		if (dadosMap == null) {
			dadosMap = new HashMap<Integer, Dado>();
		}
		return dadosMap;
	}

}
