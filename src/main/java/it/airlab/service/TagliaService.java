package it.airlab.service;

import java.util.Map;

import it.airlab.component.Risposta;
import it.airlab.model.Taglia;

public interface TagliaService {

	Risposta aggiungiTaglia(Taglia taglia);
	Risposta aggiornaTaglia(Integer id,  Map<String, String> nuoviDati);
	Risposta eliminaTaglia(Integer id);
}
