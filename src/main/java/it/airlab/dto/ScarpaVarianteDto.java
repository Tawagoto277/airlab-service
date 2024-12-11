package it.airlab.dto;

import java.math.BigDecimal;

public class ScarpaVarianteDto {

	private Integer id;
	private BigDecimal prezzo;
	private ScarpaDto scarpa;
	private TagliaDto taglia;
	private ColoreDto colore;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	public ScarpaDto getScarpa() {
		return scarpa;
	}
	public void setScarpa(ScarpaDto scarpa) {
		this.scarpa = scarpa;
	}
	public TagliaDto getTaglia() {
		return taglia;
	}
	public void setTaglia(TagliaDto taglia) {
		this.taglia = taglia;
	}
	public ColoreDto getColore() {
		return colore;
	}
	public void setColore(ColoreDto colore) {
		this.colore = colore;
	}
}
