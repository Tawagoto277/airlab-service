package it.airlab.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.airlab.model.Colore;

public interface ColoreDao extends CrudRepository<Colore, Integer>{

	Optional<Colore> findByNome(String nome);
}
