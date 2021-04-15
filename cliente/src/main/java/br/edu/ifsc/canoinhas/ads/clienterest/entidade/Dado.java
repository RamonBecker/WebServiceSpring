package br.edu.ifsc.canoinhas.ads.clienterest.entidade;

public class Dado {
	private Integer id;
	private String data;
	private String hora;
	private String temperatura;
	private String umidade;
	private String luminosidade;

	public Dado(Integer id, String data, String hora, String temperatura, String umidade, String luminosidade) {
		this.id = id;
		this.data = data;
		this.hora = hora;
		this.temperatura = temperatura;
		this.umidade = umidade;
		this.luminosidade = luminosidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getUmidade() {
		return umidade;
	}

	public void setUmidade(String umidade) {
		this.umidade = umidade;
	}

	public String getLuminosidade() {
		return luminosidade;
	}

	public void setLuminosidade(String luminosidade) {
		this.luminosidade = luminosidade;
	}

	@Override
	public String toString() {
		return "Dado [id=" + id + ", data=" + data + ", hora=" + hora + ", temperatura=" + temperatura + ", umidade="
				+ umidade + ", luminosidade=" + luminosidade + "]";
	}

}
