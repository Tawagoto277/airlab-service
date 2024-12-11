package it.airlab.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "taglia")
public class Taglia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotNull(message = "Campo numero taglia mancate")
	@DecimalMin(value = "0.0", inclusive = false, message = "Il numero di taglia deve essere maggiore di zero")
	private BigDecimal numero;

	@OneToMany(mappedBy = "taglia", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<VarianteScarpa> varianteScarpa;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getNumero() {
		return numero;
	}
	public void setNumero(BigDecimal numero) {
		this.numero = numero;
	}
	public List<VarianteScarpa> getVarianteScarpa() {
		return varianteScarpa;
	}
	public void setVarianteScarpa(List<VarianteScarpa> varianteScarpa) {
		this.varianteScarpa = varianteScarpa;
	}
}
