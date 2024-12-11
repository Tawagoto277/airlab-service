package it.airlab.dao;

import org.springframework.data.repository.CrudRepository;

import it.airlab.model.Scarpa;
import java.util.List;
import java.util.Optional;


public interface ScarpaDao extends CrudRepository<Scarpa, Integer>{

	Optional<Scarpa> findByNome(String nome);
	List<Scarpa> findAllByNuovoArrivoTrue();
	List<Scarpa> findAllByCategoria(String categoria);
	List<Scarpa> findAllByBestSeller(String bestSeller);
}
