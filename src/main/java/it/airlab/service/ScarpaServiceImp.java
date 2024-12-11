package it.airlab.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import it.airlab.component.Risposta;
import it.airlab.dao.ScarpaDao;
import it.airlab.model.Scarpa;

public class ScarpaServiceImp implements ScarpaService{

	@Autowired
	private Risposta risposta;
	
	@Autowired
	private ScarpaDao scarpaDao;
	
	@Override
	public Risposta aggiungiScarpa(Scarpa scarpa) {
		if(scarpa == null || scarpa.getNome() == null)
			return risposta.getRisposta(404, "Errore sui dati della scarpa inserita");
		Optional<Scarpa> scarpaOp = scarpaDao.findByNome(scarpa.getNome());//se ci fosse l'anno farei un controllo incrociato tra anno e nome
		if(scarpaOp.isPresent())
			return risposta.getRisposta(409, "Scarpa gia presente");
		scarpaDao.save(scarpa);
		return risposta.getRisposta(202, "Scarpa aggiunta");
	}

	@Override
	public Risposta aggiornaScarpa(Integer id, Map<String, String> nuoviDati) {
		Optional<Scarpa> scarpaOp = scarpaDao.findById(id);
		if(scarpaOp.isPresent()) {
			Scarpa scarpa = scarpaOp.get();
			
			if(nuoviDati.containsKey("nome") && nuoviDati.get("nome") != null)
				scarpa.setNome(nuoviDati.get("nome"));
			if(nuoviDati.containsKey("categoria") && nuoviDati.get("categoria") != null)
				scarpa.setCategoria(nuoviDati.get("categoria"));
			if(nuoviDati.containsKey("prezzoBase") && nuoviDati.get("prezzoBase") != null) {
				BigDecimal prezzoBase = new BigDecimal(nuoviDati.get("prezzoBase"));
				scarpa.setPrezzoBase(prezzoBase);				
			}
			if(nuoviDati.containsKey("immagine") && nuoviDati.get("immagine") != null)
				scarpa.setImmagineBase64(nuoviDati.get("immagine"));
			if(nuoviDati.containsKey("descrizione") && nuoviDati.get("descrizione") != null)
				scarpa.setDescrizione(nuoviDati.get("descrizione"));
			if(nuoviDati.containsKey("nuovoArrivo") && nuoviDati.get("nuovoArrivo") != null) {
				Boolean nuoviArrivi = Boolean.valueOf(nuoviDati.get("nuovoArrivo"));
				scarpa.setNuovoArrivo(nuoviArrivi);;				
			}
			if(nuoviDati.containsKey("bestSeller") && nuoviDati.get("bestSeller") != null)
				scarpa.setBestSeller(nuoviDati.get("bestSeller"));		
			
			scarpaDao.save(scarpa);
			return risposta.getRisposta(202, "Scarpa aggiornata");
		}
		return risposta.getRisposta(404, "Errore : scarpa non trovata");
	}

	@Override
	public Risposta eliminaScarpa(Integer id) {
		Optional<Scarpa> scarpaOp = scarpaDao.findById(id);
		if(scarpaOp.isPresent()) {
			Scarpa scarpa = scarpaOp.get();
			scarpaDao.delete(scarpa);
			return risposta.getRisposta(202, "Scarpa eliminata");
		}
		return risposta.getRisposta(404, "Errore : scarpa non trovata");
	}

}
