package it.airlab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.airlab.component.Risposta;
import it.airlab.dto.ScarpaDettaglioDto;
import it.airlab.service.VarianteScarpaService;


@RestController
@RequestMapping("/airlab/scarpa")
public class VarianteScarpaController {

	@Autowired
	private VarianteScarpaService vScarpaS;
	
	@GetMapping("/get")
	public ResponseEntity<List<ScarpaDettaglioDto>> getElencoDettaglioScarpe() {
	    return ResponseEntity.status(HttpStatus.OK).body(vScarpaS.elencoScarpeDettaglio());
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<Object> dettaglioScarpa(@PathVariable Integer id){
		Object rispostaService = vScarpaS.dettaglioVarianteScarpaCompleto(id);
		if(rispostaService instanceof Risposta) {
			Risposta risposta = (Risposta) rispostaService;
			return ResponseEntity.status(HttpStatus.OK).body(risposta);
		}
		return ResponseEntity.status(HttpStatus.OK).body(rispostaService);
	}
}
