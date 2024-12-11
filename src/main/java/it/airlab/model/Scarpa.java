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
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "scarpa")
public class Scarpa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotNull(message = "Campo nome mancante")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ'\\-\\s]{3,50}$", message = "Il nome deve contenere minimo 3 caratteri")
	private String nome;
	
	@Column
	@NotNull(message = "Campo categoria mancante")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ'\\-\\s]{3,15}$", message = "la categoria deve contenere minimo 3 caratteri")
	private String categoria;
	
	@Column
	@NotNull(message = "Campo prezzo bse mancante")
	@DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo base deve essere maggiore di zero")
	private BigDecimal prezzoBase;
	
	@Column(name = "immagine")
	private String immagineBase64;
	
	@Column(length = 1000)
	@NotNull(message = "Campo numero taglia mancate")
	@Pattern(regexp = "^[A-Za-zÀ-ÿ0-9'.,\\-\\s]{1,1000}$", message = "La descrizione contiene caratteri non validi o è troppo lunga")
	private String descrizione;
	
	@Column
	@NotNull(message = "Campo nuovo arrivo mancate")
	private Boolean nuovoArrivo;
	
	@Column
	@NotNull(message = "Campo best seller mancate")
	@Pattern(regexp = "^[1-5]$", message = "I\"La valutazione deve essere compresa tra 1 e 5")
	private String bestSeller;

	@OneToMany(mappedBy = "scarpa", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getImmagineBase64() {
		return immagineBase64;
	}
	public void setImmagineBase64(String immagineBase64) {
		this.immagineBase64 = immagineBase64;
	}
	public String getBestSeller() {
		return bestSeller;
	}
	public void setBestSeller(String bestSeller) {
		this.bestSeller = bestSeller;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public BigDecimal getPrezzoBase() {
		return prezzoBase;
	}
	public void setPrezzoBase(BigDecimal prezzoBase) {
		this.prezzoBase = prezzoBase;
	}
	public List<VarianteScarpa> getVarianteScarpa() {
		return varianteScarpa;
	}
	public void setVarianteScarpa(List<VarianteScarpa> varianteScarpa) {
		this.varianteScarpa = varianteScarpa;
	}
	public Boolean getNuovoArrivo() {
		return nuovoArrivo;
	}
	public void setNuovoArrivo(Boolean nuovoArrivo) {
		this.nuovoArrivo = nuovoArrivo;
	}
}
