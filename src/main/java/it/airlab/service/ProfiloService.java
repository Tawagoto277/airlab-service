package it.airlab.service;

import java.util.List;
import java.util.Map;

import it.airlab.component.Risposta;
import it.airlab.dto.ProfiloConIndirizzoDto;
import it.airlab.model.Profilo;

public interface ProfiloService {

	List<ProfiloConIndirizzoDto> elencoProfili();
	Object dettaglioProfiloId(Integer id);
	Risposta registrazioneProfilo(Profilo profilo);
	Risposta aggiornamentoProfilo(Integer id, Map<String, String> nuoviDati);
	Risposta eliminazioneProfilo(Integer id);
	Risposta loginProfilo(Map<String, String> credenziali);
	Risposta logoutProfilo(String token);
}
