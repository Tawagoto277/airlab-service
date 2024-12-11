package it.airlab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.airlab.component.Risposta;
import it.airlab.component.Token;
import it.airlab.dao.ProfiloDao;
import it.airlab.dto.ProfiloConIndirizzoDto;
import it.airlab.model.Profilo;

@Service
public class ProfiloServiceImp implements ProfiloService {

	@Autowired
	private ProfiloDao profiloDao;

	@Autowired
	private Risposta risposta;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private Token token;
	
	
	@Override
	public List<ProfiloConIndirizzoDto> elencoProfili() {
		List<Profilo> profili = (List<Profilo>) profiloDao.findAll();
		List<ProfiloConIndirizzoDto> profiliDto = new ArrayList<>();
		for(Profilo p : profili) {
			ProfiloConIndirizzoDto pDto = mapper.map(p, ProfiloConIndirizzoDto.class);
			profiliDto.add(pDto);
		}
		return profiliDto;
	}

	@Override
	public Object dettaglioProfiloId(Integer id) {
		Optional<Profilo> profiloOpt = profiloDao.findById(id);
		if(profiloOpt.isPresent())
			return mapper.map(profiloOpt.get(), ProfiloConIndirizzoDto.class);
		return risposta.getRisposta(404, "Profilo non trovata");
	}

	@Override
	public Risposta registrazioneProfilo(Profilo profilo) {
		if(profiloDao.findByEmail(profilo.getEmail()) != null)
			return risposta.getRisposta(409, "Email non trovata");
		profilo.setPassword(encoder.encode(profilo.getPassword()));
		profiloDao.save(profilo);
		return risposta.getRisposta(201, "Nuovo profilo registrato");
	}

	@Override
	public Risposta aggiornamentoProfilo(Integer id, Map<String, String> nuoviDati) {
		Optional<Profilo> profiloOp = profiloDao.findById(id);
		if(profiloOp.isPresent()) {
			Profilo profilo = profiloOp.get();
			
			if (nuoviDati.containsKey("nome") && nuoviDati.get("nome") != null)
				profilo.setNome(nuoviDati.get("nome"));
			if (nuoviDati.containsKey("cognome") && nuoviDati.get("cognome") != null)
				profilo.setCognome(nuoviDati.get("cognome"));
			if (nuoviDati.containsKey("email") && nuoviDati.get("email") != null)
				profilo.setEmail(nuoviDati.get("email"));
			if (nuoviDati.containsKey("telefono") && nuoviDati.get("telefono") != null)
				profilo.setTelefono(nuoviDati.get("telefono"));

			profiloDao.save(profilo);
			return risposta.getRisposta(202, "Dati profilo aggiornati");
		}
		return risposta.getRisposta(404, "Profilo non trovato");
	}

	@Override
	public Risposta eliminazioneProfilo(Integer id) {
		Optional<Profilo> profiloOp = profiloDao.findById(id);
		if(profiloOp.isPresent()) {
			Profilo profilo = profiloOp.get();
			
			profiloDao.delete(profilo);
			return risposta.getRisposta(202, "Profilo eliminato");
		}		
		return risposta.getRisposta(404, "Profilo non trovato");
	}

	@Override
	public Risposta loginProfilo(Map<String, String> credenziali) {
		Profilo profilo = profiloDao.findByEmail(credenziali.get("email"));
		
		if(profilo == null || !encoder.matches(credenziali.get("password"), profilo.getPassword()))
			return risposta.getRisposta(401, "Login non autorizzato");
		
		String tokenProfilo = token.tokenProfilo(profilo.getEmail());
		profilo.setToken(tokenProfilo);
		profiloDao.save(profilo);
		return risposta.getRisposta(202, tokenProfilo);
	}

	@Override
	public Risposta logoutProfilo(String token) {
		Profilo profilo = profiloDao.findByToken(token);
		if(profilo == null)
			return risposta.getRisposta(401, "Operazione non autorizzata");
		profilo.setToken(null);
		profiloDao.save(profilo);
		return risposta.getRisposta(202, "Logout effetuato");
	}

	
}
