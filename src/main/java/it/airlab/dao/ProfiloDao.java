package it.airlab.dao;

import org.springframework.data.repository.CrudRepository;

import it.airlab.model.Profilo;

public interface ProfiloDao extends CrudRepository<Profilo, Integer>{

	Profilo findByEmail(String email);
	Profilo findByToken(String token);
}
