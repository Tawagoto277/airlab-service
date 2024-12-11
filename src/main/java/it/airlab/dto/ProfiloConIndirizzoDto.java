package it.airlab.dto;

import java.util.List;

public class ProfiloConIndirizzoDto {

	private Integer id;
	private String nome;
	private String cognome;
	private String telefono;
	private String email;
	private List<IndirizzoDto> indirizzi;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<IndirizzoDto> getIndirizzi() {
		return indirizzi;
	}
	public void setIndirizzi(List<IndirizzoDto> indirizzi) {
		this.indirizzi = indirizzi;
	}
}
