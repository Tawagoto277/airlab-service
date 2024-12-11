package it.airlab.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.airlab.component.Risposta;
import it.airlab.dao.ColoreDao;
import it.airlab.model.Colore;

@Service
public class ColoreServiceImp implements ColoreService{

	@Autowired
	private Risposta risposta;
	
	@Autowired
	private ColoreDao coloreDao;
	
	@Override
	public Risposta aggiungiColore(Colore colore) {
		if(colore == null || colore.getNome() == null || colore.getEsadecimale() == null)
			return risposta.getRisposta(404, "Errore sui dati di colore");
		Optional<Colore> coloreOp = coloreDao.findByNome(colore.getNome());
		if(coloreOp.isPresent())
			return risposta.getRisposta(409, "Colore gia' presente nel database");
		
		coloreDao.save(colore);
		return risposta.getRisposta(202, "Colore aggiunto");
	}

	@Override
	public Risposta aggiornaColore(Integer id, Map<String, String> nuoviDati) {
		Optional<Colore> coloreOp = coloreDao.findById(id);
		if(coloreOp.isPresent()) {
			Colore colore = coloreOp.get();
			
			if(nuoviDati.containsKey("nome") && nuoviDati.get("nome") != null)
				colore.setNome(nuoviDati.get("nome"));
			if(nuoviDati.containsKey("esadecimale") && nuoviDati.get("esadecimale") != null)
				colore.setEsadecimale("esadecimale");
				
			coloreDao.save(colore);
			return risposta.getRisposta(202, "Dati colore aggiornati");
		}
		return risposta.getRisposta(404, "Errore : Colore non trovato");
	}

	@Override
	public Risposta eliminaColore(Integer id) {
		Optional<Colore> coloreOp = coloreDao.findById(id);
		if(coloreOp.isPresent()) {
			Colore colore = coloreOp.get();
			coloreDao.delete(colore);
			return risposta.getRisposta(202, "Colore eliminato");			
		}
		return risposta.getRisposta(404, "Colore non trovato");
	}

}
