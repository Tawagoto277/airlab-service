package it.airlab.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "variante_scarpa")
public class VarianteScarpa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotNull(message = "Campo prezzo bse mancante")
	@DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo deve essere maggiore di zero")
	private BigDecimal prezzo;
	
	@ManyToOne
	@JoinColumn(name = "id_scarpa", nullable = false)
	private Scarpa scarpa;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_taglia", nullable = false)
	private Taglia taglia;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_colore", nullable = false)
	private Colore colore;

	
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

	public Scarpa getScarpa() {
		return scarpa;
	}

	public void setScarpa(Scarpa scarpa) {
		this.scarpa = scarpa;
	}

	public Taglia getTaglia() {
		return taglia;
	}

	public void setTaglia(Taglia taglia) {
		this.taglia = taglia;
	}

	public Colore getColore() {
		return colore;
	}

	public void setColore(Colore colore) {
		this.colore = colore;
	}
}
