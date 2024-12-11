package it.airlab.model;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "colore")
public class Colore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true)
	@NotNull(message = "Campo nome colore mancante")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ'\\-\\s]{2,50}$", message = "Il campo nome colore contiene caratteri non validi")
	private String nome;
	
	@Column
	@NotNull(message = "Campo colore esadecimale mancante")
	@Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Il campo nome colore contiene caratteri non validi")
	private String esadecimale;

	@OneToMany(mappedBy = "colore", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<VarianteScarpa> varianteScarpa;
	
	
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
	public String getEsadecimale() {
		return esadecimale;
	}
	public void setEsadecimale(String esadecimale) {
		this.esadecimale = esadecimale;
	}
	public List<VarianteScarpa> getVarianteScarpa() {
		return varianteScarpa;
	}
	public void setVarianteScarpa(List<VarianteScarpa> varianteScarpa) {
		this.varianteScarpa = varianteScarpa;
	}	
}
