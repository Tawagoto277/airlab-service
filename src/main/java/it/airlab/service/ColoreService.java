package it.airlab.service;

import java.util.Map;

import it.airlab.component.Risposta;
import it.airlab.model.Colore;

public interface ColoreService {

	Risposta aggiungiColore(Colore colore);
	Risposta aggiornaColore(Integer id,  Map<String, String> nuoviDati);
	Risposta eliminaColore(Integer id);
}
