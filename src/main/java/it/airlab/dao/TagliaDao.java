package it.airlab.dao;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.airlab.model.Taglia;

public interface TagliaDao extends CrudRepository<Taglia, Integer>{

	Optional<Taglia> findByNumero(BigDecimal numero);
}
