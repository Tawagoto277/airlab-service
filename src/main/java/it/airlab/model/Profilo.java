package it.airlab.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "profilo")
public class Profilo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotNull(message = "Campo nome mancante")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ'\\-\\s]{3,50}$", message = "Il nome deve contenere minimo 3 caratteri")
	private String nome;
	
	@Column
	@NotNull(message = "Campo cognome mancante")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ'\\-\\s]{3,50}$", message = "Il cognome deve contenere minimo 3 caratteri")
	private String cognome;
	
	@Column
	@NotNull(message = "Campo telefono mancante")
    @Pattern(regexp = "^[+]?[0-9 ()-]{6,20}$", message = "Formato numero di telefono non valido")
	private String telefono;
	
	@Column
	@NotNull(message = "Campo email mancante")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Formato email non valido")
	private String email;
	
	@Column
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message = "Campo password mancante")
	private String password;
	
	@Column
	@JsonIgnore
	private String token;
	
	@OneToMany(mappedBy = "profilo", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Indirizzo> indirizzi = new ArrayList<>();

	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Indirizzo> getIndirizzi() {
		return indirizzi;
	}

	public void setIndirizzi(List<Indirizzo> indirizzi) {
		this.indirizzi = indirizzi;
	}
}
