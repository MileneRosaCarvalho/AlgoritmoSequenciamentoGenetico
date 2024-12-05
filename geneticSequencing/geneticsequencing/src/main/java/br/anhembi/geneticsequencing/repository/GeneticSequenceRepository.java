package br.anhembi.geneticsequencing.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import br.anhembi.geneticsequencing.model.GeneticSequence;

public interface GeneticSequenceRepository extends JpaRepository<GeneticSequence, Long>{   

}