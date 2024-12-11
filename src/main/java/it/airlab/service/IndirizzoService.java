package it.airlab.service;

import java.util.Map;

import it.airlab.component.Risposta;
import it.airlab.model.Indirizzo;

public interface IndirizzoService {

	Risposta registrazioneIndirizzo(Integer profiloId, Indirizzo indirizzo);
	Risposta aggiornamentoIndirizzo(Integer profiloId, Integer id, Map<String, String> nuoviDati);
	Risposta eliminazioneIndirizzo(Integer profiloId, Integer id);
}
