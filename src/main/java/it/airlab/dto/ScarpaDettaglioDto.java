package it.airlab.dto;

import java.math.BigDecimal;
import java.util.Set;

public class ScarpaDettaglioDto {

	private ScarpaDto scarpa;
	private Set<BigDecimal> taglie;
	private Set<String> colori;
	
	
	public ScarpaDto getScarpa() {
		return scarpa;
	}
	public void setScarpa(ScarpaDto scarpa) {
		this.scarpa = scarpa;
	}
	public Set<BigDecimal> getTaglie() {
		return taglie;
	}
	public void setTaglie(Set<BigDecimal> taglie) {
		this.taglie = taglie;
	}
	public Set<String> getColori() {
		return colori;
	}
	public void setColori(Set<String> colori) {
		this.colori = colori;
	}
 	
}
