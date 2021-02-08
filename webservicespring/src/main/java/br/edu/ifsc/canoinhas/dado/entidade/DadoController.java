package br.edu.ifsc.canoinhas.dado.entidade;

import java.io.IOException;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DadoController {

	@RequestMapping(value = "*", method = RequestMethod.GET)
	@ResponseBody
	public String getFallback() {
		return "Um endpont válido deve ser especificado.";
	}

	@RequestMapping(value = "/dado/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<Integer, Dado> todosDados() {
		return DadoDB.getInstance().getDadosMap();
	}

	@RequestMapping(value = "/dado/busca", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Dado buscaDado(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		return DadoDB.getInstance().buscaDado(id);
	}

	@RequestMapping(value = "/dado/buscaid/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Dado dado(@PathVariable Integer id) {
		return DadoDB.getInstance().buscaDado(id);
	}

	@RequestMapping(value = "/dado/adiciona", method = RequestMethod.POST, // MESMA IDEIA PARA O PUT
			produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String adicionaDado(@RequestBody String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Dado d = objectMapper.readValue(json, Dado.class);
			DadoDB.getInstance().adicionaDado(d.getId(), d);
			return "Sucesso";
		} catch (JsonParseException e) {
			e.printStackTrace();
			return "Erro";
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return "Erro";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping(value = "/dado/apaga", method = RequestMethod.DELETE)
	@ResponseBody
	public String apagaDado(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		DadoDB.getInstance().deletarDado(id);
		return "Sucesso";
	}
}
