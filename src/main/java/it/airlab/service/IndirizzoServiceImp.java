package it.airlab.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.airlab.component.Risposta;
import it.airlab.dao.IndirizzoDao;
import it.airlab.dao.ProfiloDao;
import it.airlab.model.Indirizzo;
import it.airlab.model.Profilo;

@Service
public class IndirizzoServiceImp implements IndirizzoService{

	@Autowired
	private IndirizzoDao indirizzoDao;
	
	@Autowired
	private ProfiloDao profiloDao;
	
	@Autowired
	private Risposta risposta;
	
	@Override
	@Transactional
	public Risposta registrazioneIndirizzo(Integer profiloId, Indirizzo indirizzo) {
		Optional<Profilo> profiloOp = profiloDao.findById(profiloId);
		if(profiloOp.isPresent()) {
			Profilo profilo = profiloOp.get();
			
			indirizzo.setProfilo(profilo);
			profilo.getIndirizzi().add(indirizzo);
			
			indirizzoDao.save(indirizzo);
			return risposta.getRisposta(200, "Indirizzo registrato");
		} 
		return risposta.getRisposta(404, "Profilo non trovato");
	}

	@Override
	public Risposta aggiornamentoIndirizzo(Integer profiloId, Integer id, Map<String, String> nuoviDati) {
		Optional<Profilo> profiloOp = profiloDao.findById(profiloId);
		if(profiloOp.isPresent()) {
			Profilo profilo = profiloOp.get();
			Optional<Indirizzo> indirizzoOp = indirizzoDao.findById(id);
			
			if(indirizzoOp.isPresent()) {
				Indirizzo indirizzo = indirizzoOp.get();
				
				if (nuoviDati.containsKey("via") && nuoviDati.get("via") != null)
	                indirizzo.setVia(nuoviDati.get("via"));
	            if (nuoviDati.containsKey("civico") && nuoviDati.get("civico") != null)
	                indirizzo.setCivico(nuoviDati.get("civico"));
	            if (nuoviDati.containsKey("cap") && nuoviDati.get("cap") != null)
	                indirizzo.setCap(nuoviDati.get("cap"));
	            if (nuoviDati.containsKey("citta") && nuoviDati.get("citta") != null)
	                indirizzo.setCitta(nuoviDati.get("citta"));
	            if (nuoviDati.containsKey("provincia") && nuoviDati.get("provincia") != null)
	                indirizzo.setProvincia(nuoviDati.get("provincia"));
	            if (nuoviDati.containsKey("regione") && nuoviDati.get("regione") != null)
	                indirizzo.setRegione(nuoviDati.get("regione"));
	            if (nuoviDati.containsKey("nazione") && nuoviDati.get("nazione") != null)
	                indirizzo.setNazione(nuoviDati.get("nazione"));
				
				profiloDao.save(profilo);
				
				return risposta.getRisposta(202, "dati indirizzo aggiornati");
			}
			return risposta.getRisposta(404, "Indirizzo non trovato");
		} 
		return risposta.getRisposta(404, "Profilo non trovato");
	}

	@Override
	public Risposta eliminazioneIndirizzo(Integer profiloId, Integer id) {
		Optional<Profilo> profiloOp = profiloDao.findById(profiloId);
		if(profiloOp.isPresent()) {
			Optional<Indirizzo> indirizzoOp = indirizzoDao.findById(id);
			
			if(indirizzoOp.isPresent()) {
				Indirizzo indirizzo = indirizzoOp.get();
				indirizzoDao.delete(indirizzo);
				
				return risposta.getRisposta(202, "Indirizzo eliminato");
			}
			return risposta.getRisposta(404, "Indirizzo non trovato");
		} 
		return risposta.getRisposta(404, "Profilo non trovato");
	}

}
