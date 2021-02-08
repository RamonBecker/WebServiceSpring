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

	public Map<Integer, Dado> getDadosMap() {
		if (dadosMap == null) {
			dadosMap = new HashMap<Integer, Dado>();
		}
		return dadosMap;
	}

}
