package it.airlab.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.airlab.component.Risposta;
import it.airlab.dao.TagliaDao;
import it.airlab.model.Taglia;

@Service
public class TagliaServiceImp implements TagliaService {

	@Autowired
	private Risposta risposta;
	
	@Autowired
	private TagliaDao tagliaDao;
	
	@Override
	public Risposta aggiungiTaglia(Taglia taglia) {
		if(taglia == null || taglia.getNumero() == null)//controllo che taglia non sia vuota o numero null
			return risposta.getRisposta(404, "Errore sui dati di taglia");
		Optional<Taglia> tagliaOp = tagliaDao.findByNumero(taglia.getNumero());// se taglia esiste gia'
		if(tagliaOp.isPresent())
			return risposta.getRisposta(409, "Taglia gia' presente");
		tagliaDao.save(taglia);//carico taglia e ritorno la risposta
		return risposta.getRisposta(202, "Taglia aggiunta");
	}

	@Override
	public Risposta aggiornaTaglia(Integer id, Map<String, String> nuoviDati) {
		Optional<Taglia> tagliaOp = tagliaDao.findById(id);//cerco taglia per id
		if(tagliaOp.isPresent()) {
			Taglia taglia = tagliaOp.get();//traferisco tagliaOp a taglia
			
			//controllo che i vuovi dati non siano vuoti 
			if(nuoviDati.containsKey("numero") && nuoviDati.get("numero") != null) {
				//converto la stringa in bigdeciman
				BigDecimal numeroTaglia = new BigDecimal(nuoviDati.get("numero"));
				taglia.setNumero(numeroTaglia);
			}
			
			tagliaDao.save(taglia);//carico taglia e invio risposta
			return risposta.getRisposta(202, "Taglia aggiornata");
		}
		return risposta.getRisposta(404, "Errore : Taglia non trovata");
	}

	@Override
	public Risposta eliminaTaglia(Integer id) {
		Optional<Taglia> tagliaOp = tagliaDao.findById(id);//cerco per id taglia 
		if(tagliaOp.isPresent()) {
			Taglia taglia = tagliaOp.get();
			tagliaDao.delete(taglia);//e elimino
			return risposta.getRisposta(202, "Taglia eliminata");
		}
		return risposta.getRisposta(404, "Errore : Taglia non trovata");
	}

}
