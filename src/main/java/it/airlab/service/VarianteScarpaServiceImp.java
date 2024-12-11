package it.airlab.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.airlab.component.Risposta;
import it.airlab.dao.ColoreDao;
import it.airlab.dao.ScarpaDao;
import it.airlab.dao.TagliaDao;
import it.airlab.dao.VarianteScarpaDao;
import it.airlab.dto.ScarpaDettaglioDto;
import it.airlab.dto.ScarpaDto;
import it.airlab.dto.ScarpaVarianteDto;
import it.airlab.model.VarianteScarpa;

@Service
public class VarianteScarpaServiceImp implements VarianteScarpaService{
	
	@Autowired
	private VarianteScarpaDao varScarpaDao;
	
	@Autowired
	private ScarpaDao scarpaDao;
	
	@Autowired
	private ColoreDao coloreDao;
	
	@Autowired
	private TagliaDao tagliaDao;

	@Autowired 
	private Risposta risposta;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<ScarpaVarianteDto> elencoVarianteScarpa() {
		List<VarianteScarpa> varScarpa = (List<VarianteScarpa>) varScarpaDao.findAll();
		List<ScarpaVarianteDto> varScarpaDto = new ArrayList<>();
		
		for(VarianteScarpa vp : varScarpa) {
			ScarpaVarianteDto vsDto = mapper.map(vp, ScarpaVarianteDto.class);
			varScarpaDto.add(vsDto);
		}
		return varScarpaDto;
	}
	
	@Override
	public Object dettaglioVarianteScarpaCompleto(Integer id) {
		Optional<VarianteScarpa> varScarpaOp = varScarpaDao.findById(id);
		
		if(varScarpaOp.isPresent())
			return mapper.map(varScarpaOp.get(), ScarpaVarianteDto.class);
		
		return risposta.getRisposta(404, "Specifica scarpa non trovata");
	}

	@Override
	public List<ScarpaDettaglioDto> elencoScarpeDettaglio() {
		List<VarianteScarpa> varianti = varScarpaDao.findAll();
		Map<Integer, ScarpaDettaglioDto> dettaglioMappa = new HashMap<Integer, ScarpaDettaglioDto>();
		
		for(VarianteScarpa v : varianti) {
			Integer idScarpa = v.getScarpa().getId();
			
			ScarpaDettaglioDto dettaglio = dettaglioMappa.computeIfAbsent(idScarpa, id -> {
				ScarpaDettaglioDto nuovoDettaglio = new ScarpaDettaglioDto();
				nuovoDettaglio.setScarpa(mapper.map(v.getScarpa(), ScarpaDto.class));
				nuovoDettaglio.setColori(new TreeSet<>());
	            nuovoDettaglio.setTaglie(new TreeSet<>());
	            return nuovoDettaglio;
			});
			dettaglio.getColori().add(v.getColore().getNome());
	        dettaglio.getTaglie().add(v.getTaglia().getNumero());
		}
		return new ArrayList<>(dettaglioMappa.values());
	}
	
	@Override
	public List<ScarpaVarianteDto> dettaglioTaglieVarianteScarpa(BigDecimal numero) {
		List<VarianteScarpa> varScarpa = (List<VarianteScarpa>) varScarpaDao.findByTagliaNumero(numero);
		List<ScarpaVarianteDto> varScarpaDto = new ArrayList<>();
		
		for(VarianteScarpa vp : varScarpa) {
			ScarpaVarianteDto vsDto = mapper.map(vp, ScarpaVarianteDto.class);
			varScarpaDto.add(vsDto);
		}
		return varScarpaDto;
	}

	@Override
	public Risposta aggiungiScarpaVariante(VarianteScarpa varianteScarpa) {
		if(varianteScarpa.getScarpa() == null ||  varianteScarpa.getTaglia()==null ||  varianteScarpa.getColore()== null)
			return risposta.getRisposta(404, "Dati incompleti per la variazione");
		
		Optional<VarianteScarpa> varScarpaOp = varScarpaDao.findByScarpaAndTagliaAndColore(
				varianteScarpa.getScarpa(), varianteScarpa.getTaglia(), varianteScarpa.getColore());
		
		if(!varScarpaOp.isPresent())
			return risposta.getRisposta(409, "Scarpa specifica gia presente");
		
		varScarpaDao.save(varianteScarpa);
		return risposta.getRisposta(201, "Variazione scarpa aggiunta");
	}

	@Override
	public Risposta aggiornaScarpaVariante(Integer id, Map<String, Integer> nuovidati) {
		Optional<VarianteScarpa> varScarpaOp = varScarpaDao.findById(id);
		if(varScarpaOp.isPresent()) {
			VarianteScarpa varScarpa = varScarpaOp.get();
			
			if(nuovidati.containsKey("scarpa") && nuovidati.get("scarpa") != null)			
				scarpaDao.findById(nuovidati.get("scarpa")).ifPresent(scarpaOp -> varScarpa.setScarpa(scarpaOp));
			if(nuovidati.containsKey("colore") && nuovidati.get("colore") != null)
				coloreDao.findById(nuovidati.get("colore")).ifPresent(coloreOp -> varScarpa.setColore(coloreOp));
			if(nuovidati.containsKey("taglia") && nuovidati.get("taglia") != null)
				tagliaDao.findById(nuovidati.get("taglia")).ifPresent(tagliaOp -> varScarpa.setTaglia(tagliaOp));
			
			varScarpaDao.save(varScarpa);
			return risposta.getRisposta(202, "Scarpa specifica aggiornata");
		}
		return risposta.getRisposta(404, "Specifica scarpa non trovata");
	}

	@Override
	public Risposta eliminaScarpaVariante(Integer id) {
		Optional<VarianteScarpa> varScarpaOp = varScarpaDao.findById(id);
		
		if(varScarpaOp.isPresent()) {
			VarianteScarpa varScarpa = varScarpaOp.get();
			
			varScarpaDao.delete(varScarpa);
			return risposta.getRisposta(202, "Scarpa specifica eliminata");
		}
		return risposta.getRisposta(404, "Specifica scarpa non trovata");
	}
}
