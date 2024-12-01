package br.anhembi.geneticsequencing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GeneticSequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, columnDefinition = "LONGTEXT")
    private String sequenceString;

    @Column(nullable = false, unique = true, columnDefinition = "LONGTEXT")
    private String name;

    
    @Column(nullable = false, unique = true, columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false, unique = true, columnDefinition = "LONGTEXT")
    private String link;
 
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSequence() {
        return sequenceString;
    }
    public void setSequence(String sequenceString) {
        this.sequenceString = sequenceString;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
}