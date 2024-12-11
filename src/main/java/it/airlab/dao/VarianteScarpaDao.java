package it.airlab.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.airlab.model.Colore;
import it.airlab.model.Scarpa;
import it.airlab.model.Taglia;
import it.airlab.model.VarianteScarpa;

public interface VarianteScarpaDao extends JpaRepository<VarianteScarpa, Integer> {

    List<VarianteScarpa> findByScarpaId(Integer idScarpa);
    List<VarianteScarpa> findByColoreId(Integer idColore);
    List<VarianteScarpa> findByTagliaId(Integer idTaglia);
    List<VarianteScarpa> findByTagliaNumero(BigDecimal numero);
    Optional<VarianteScarpa> findByScarpaAndTagliaAndColore(Scarpa scarpa, Taglia taglia, Colore colore);
}
