package br.anhembi.geneticsequencing.repository;

import org.springframework.data.jpa.repository.JpaRepository; //Importando a interface JpaRepository que faz parte do Spring Data JPA. Ela fornece uma série de métodos prontos para operações de persistência em um banco de dados (CRUD — criar, ler, atualizar, deletar).
import br.anhembi.geneticsequencing.model.GeneticSequence; //A classe GeneticSequence é o modelo (entidade) que representa a tabela.

//Interface do Spring Data JPA, que é usada para facilitar a interação com um banco de dados relacional, utilizando as funcionalidades do JPA (Java Persistence API).
public interface GeneticSequenceRepository extends JpaRepository<GeneticSequence, Long>{   

}