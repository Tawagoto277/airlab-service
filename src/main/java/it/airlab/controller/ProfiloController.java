package it.airlab.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.airlab.component.EccezioneValidazione;
import it.airlab.component.Risposta;
import it.airlab.dto.ProfiloConIndirizzoDto;
import it.airlab.model.Indirizzo;
import it.airlab.model.Profilo;
import it.airlab.service.IndirizzoService;
import it.airlab.service.ProfiloService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/airlab/profilo")
public class ProfiloController {

	@Autowired
	private ProfiloService profiloS;
	
	@Autowired
	private IndirizzoService indirizzoS;
	
	@GetMapping("/get")
	public ResponseEntity<List<ProfiloConIndirizzoDto>> elencoProfili(){
		return ResponseEntity.status(HttpStatus.OK).body(profiloS.elencoProfili());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> dettaglioProfiloId(@PathVariable Integer id){
		Object rispostaService = profiloS.dettaglioProfiloId(id);
		if(rispostaService instanceof Risposta) {
			Risposta risposta = (Risposta) rispostaService;
			return ResponseEntity.status(HttpStatus.OK).body(risposta);
		}
		return ResponseEntity.status(HttpStatus.OK).body(rispostaService);
	}
	
	@PutMapping("/login")
	public ResponseEntity<Risposta> loginProfilo(@RequestBody Map<String, String> credenziali){
		if(!credenziali.containsKey("email"))
			throw new EccezioneValidazione("email", "Campo Email mancante");
		if(!credenziali.containsKey("password"))
			throw new EccezioneValidazione("password", "Campo Password mancante");
		
		Risposta risposta = profiloS.loginProfilo(credenziali);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<Risposta> logoutProfilo(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
		Risposta risposta = profiloS.logoutProfilo(token.split(" ")[1]);
		return ResponseEntity.status(risposta.getCodice()).body(risposta);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Risposta> registrazioneProfilo(@Valid @RequestBody Profilo profilo){
		if(!profilo.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,50}$"))
			throw new EccezioneValidazione("profilo.password", "Password inadeguata");
		Risposta risp = profiloS.registrazioneProfilo(profilo);
		return ResponseEntity.status(risp.getCodice()).body(risp);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Risposta> aggiornamentoProfilo(@PathVariable Integer id, @RequestBody Map<String, String> nuoviDati){
		Map<String, String> campiValidazione = Map.of(
				"nome", "^[a-zA-ZÀ-ÿ'\\-\\s]{3,50}$",
				"cognome", "^[a-zA-ZÀ-ÿ'\\-\\s]{3,50}$",
				"email", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
				"telefono", "^[+]?[0-9 ()-]{6,20}$");
		
		for(Map.Entry<String , String> entry : nuoviDati.entrySet()) {
			String campo = entry.getKey();
			String valore  = entry.getValue();
			
			if (campiValidazione.containsKey(campo)) {
	            String regex = campiValidazione.get(campo);

	            if (valore == null || !valore.matches(regex)) {
	                throw new EccezioneValidazione(campo, "Errore nel campo " + campo + ": valore non valido");
	            }
	        } else {
	            throw new EccezioneValidazione(campo, "Campo non riconosciuto: " + campo);
	        }
		}
		
		Risposta risp = profiloS.aggiornamentoProfilo(id, nuoviDati);
		return ResponseEntity.status(risp.getCodice()).body(risp);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Risposta> eliminaProfilo(@PathVariable Integer id){
		Risposta risp = profiloS.eliminazioneProfilo(id);		
		return ResponseEntity.status(risp.getCodice()).body(risp);
	}
	
	@PostMapping("/{idProfilo}/indirizzo")
	public ResponseEntity<Risposta> aggiungiIndirizzo(@PathVariable Integer idProfilo, @RequestBody Indirizzo indirizzo){
		if(idProfilo == null || indirizzo == null || indirizzo.getVia() == null)
			throw new EccezioneValidazione("profilo", "Errore sul profilo");
		Risposta risp = indirizzoS.registrazioneIndirizzo(idProfilo, indirizzo);
		return ResponseEntity.status(risp.getCodice()).body(risp);
	}
	
	@PutMapping("/{idProfilo}/indirizzo/update/{idIndirizzo}")
	public ResponseEntity<Risposta> aggiornamentoIndirizzo(@PathVariable Integer idProfilo, @PathVariable Integer idIndirizzo, @RequestBody Map<String, String> nuoviDati){
		if(idProfilo == null || idIndirizzo == null)
			throw new EccezioneValidazione("profilo", "Errore sul profilo");
		
		Map<String, String> campiValidazione = Map.of(
				"via", "^[A-Za-z0-9'.,\\-\\s]{2,100}$",
				"civico", "^[0-9A-Za-z]{1,10}$",
				"cap", "\\d{5}",
				"citta", "^[A-Za-zÀ-ÿ'\\-\\s]{2,50}$",
				"provincia", "^[A-Z]{2}$",
				"regione", "^[A-Za-zÀ-ÿ'\\-\\s]{2,50}$",
				"nazione", "^[A-Za-zÀ-ÿ'\\-\\s]{2,50}$");
		
		for(Map.Entry<String , String> entry : nuoviDati.entrySet()) {
			String campo = entry.getKey();
			String valore  = entry.getValue();
			
			if (campiValidazione.containsKey(campo)) {
	            String regex = campiValidazione.get(campo);

	            if (valore == null || !valore.matches(regex)) {
	                throw new EccezioneValidazione(campo, "Errore nel campo " + campo + ": valore non valido");
	            }
	        } else {
	            throw new EccezioneValidazione(campo, "Campo non riconosciuto: " + campo);
	        }
		}
		
		Risposta risp = indirizzoS.aggiornamentoIndirizzo(idProfilo, idIndirizzo, nuoviDati);
		return ResponseEntity.status(risp.getCodice()).body(risp);
	}
	
	@DeleteMapping("/{idProfilo}/indirizzo/delete/{idIndirizzo}")
	public ResponseEntity<Risposta> eliminaIndirizzo(@PathVariable Integer idProfilo, @PathVariable Integer idIndirizzo){
		Risposta risp = indirizzoS.eliminazioneIndirizzo(idProfilo, idIndirizzo);
		return ResponseEntity.status(risp.getCodice()).body(risp);
	}
 	
	@ExceptionHandler(EccezioneValidazione.class)
	public ResponseEntity<Map<String, String>> gestioneValidazione(EccezioneValidazione e){
		Map<String, String> registroErrori = new HashMap<>();
		registroErrori.put(e.getCAMPO(), e.getMESSAGGIO());
		return ResponseEntity.badRequest().body(registroErrori);
	}
}
