package it.airlab.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import it.airlab.component.Risposta;
import it.airlab.dto.ScarpaDettaglioDto;
import it.airlab.dto.ScarpaVarianteDto;
import it.airlab.model.VarianteScarpa;

public interface VarianteScarpaService {

	List<ScarpaVarianteDto> elencoVarianteScarpa();
	Object dettaglioVarianteScarpaCompleto(Integer id);
	List<ScarpaDettaglioDto> elencoScarpeDettaglio();
	List<ScarpaVarianteDto> dettaglioTaglieVarianteScarpa(BigDecimal numero);
	Risposta aggiungiScarpaVariante(VarianteScarpa varianteScarpa); 
	Risposta aggiornaScarpaVariante(Integer id, Map<String, Integer> nuovidati); 
	Risposta eliminaScarpaVariante(Integer id); 
}
