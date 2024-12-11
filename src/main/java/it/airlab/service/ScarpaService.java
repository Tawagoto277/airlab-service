package it.airlab.service;

import java.util.Map;

import it.airlab.component.Risposta;
import it.airlab.model.Scarpa;

public interface ScarpaService {

	Risposta aggiungiScarpa(Scarpa scarpa);
	Risposta aggiornaScarpa(Integer id, Map<String, String> nuoviDati);
	Risposta eliminaScarpa(Integer id);
}
