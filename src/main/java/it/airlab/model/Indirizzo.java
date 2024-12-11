package it.airlab.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "indirizzo")
public class Indirizzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotNull(message = "Campo via mancante")
    @Pattern(regexp = "^[A-Za-z0-9'.,\\-\\s]{2,100}$", message = "Il campo via contiene caratteri non validi")
	private String via;
	
	@Column
	@NotNull(message = "Campo civico mancante")
    @Pattern(regexp = "^[0-9A-Za-z]{1,10}$", message = "Il campo civico contiene caratteri non validi")
	private String civico;
	
	@Column
	@NotNull(message = "Campo cap mancante")
	@Pattern(regexp = "\\d{5}", message = "Il CAP deve contenere esattamente 5 cifre")
	private String cap;
	
	@Column
	@NotNull(message = "Campo citta mancante")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ'\\-\\s]{2,50}$", message = "Il campo città contiene caratteri non validi")
	private String citta;
	
	@Column
	@NotNull(message = "Campo provincia mancante")
    @Pattern(regexp = "^[A-Z]{2}$", message = "La provincia deve essere composta da 2 lettere maiuscole")
	private String provincia;
	
	@Column
	@NotNull(message = "Campo regione mancante")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ'\\-\\s]{2,50}$", message = "Il campo regione contiene caratteri non validi")
	private String regione;
	
	@Column
	@NotNull(message = "Campo nazione mancante")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ'\\-\\s]{2,50}$", message = "Il campo nazione contiene caratteri non validi")
	private String nazione;
	
	@ManyToOne
    @JoinColumn(name = "id_profilo", nullable = false)
    private Profilo profilo;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getCivico() {
		return civico;
	}
	public void setCivico(String civico) {
		this.civico = civico;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	public Profilo getProfilo() {
		return profilo;
	}
	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
}
