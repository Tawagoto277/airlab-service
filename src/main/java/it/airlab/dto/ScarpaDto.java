package it.airlab.dto;

import java.math.BigDecimal;

public class ScarpaDto {

	private Integer id;
	private String nome;
	private String categoria;
	private BigDecimal prezzoBase;
	private String immagineBase64;
	private String descrizione;
	private Boolean nuovoArrivo;
	private String bestSeller;
	
	
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public BigDecimal getPrezzoBase() {
		return prezzoBase;
	}
	public void setPrezzoBase(BigDecimal prezzoBase) {
		this.prezzoBase = prezzoBase;
	}
	public String getImmagineBase64() {
		return immagineBase64;
	}
	public void setImmagineBase64(String immagineBase64) {
		this.immagineBase64 = immagineBase64;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getBestSeller() {
		return bestSeller;
	}
	public void setBestSeller(String bestSeller) {
		this.bestSeller = bestSeller;
	}
	public Boolean getNuovoArrivo() {
		return nuovoArrivo;
	}
	public void setNuovoArrivo(Boolean nuovoArrivo) {
		this.nuovoArrivo = nuovoArrivo;
	}
}
