package br.anhembi.geneticsequencing.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.anhembi.geneticsequencing.list.MyList;
import br.anhembi.geneticsequencing.model.GeneticSequence;
import br.anhembi.geneticsequencing.repository.GeneticSequenceRepository;
import br.anhembi.geneticsequencing.trie.*;
import br.anhembi.geneticsequencing.model.dto.Response;

@Service 
public class GeneticSequenceService {
    
    @Autowired 
    private GeneticSequenceRepository geneticSequenceRepository;

    public List <GeneticSequence> getAllSequences(){ 
        MyList<GeneticSequence> listGeneticSequences = new MyList<>();
        List<GeneticSequence> storedGeneticSequences = geneticSequenceRepository.findAll(); 
        
        for (GeneticSequence seq : storedGeneticSequences) { 
            listGeneticSequences.add(seq);
        }
        return listGeneticSequences.allElements(); 
    }
  
    public Response calculateMatchPercentage(String inputSequence){
        MyList<GeneticSequence> listGeneticSequences = new MyList<>();
        List<GeneticSequence> storedGeneticSequences = geneticSequenceRepository.findAll();

        for (GeneticSequence seq : storedGeneticSequences) {
            listGeneticSequences.add(seq);
        }

        double maxMatchPercentage = 0; 
        GeneticSequence bestMatchSequence = null; 
      
        for (GeneticSequence storedSequence : listGeneticSequences.allElements()){
            SuffixTrie trie = new SuffixTrie();
            trie.insert(storedSequence.getSequence());
           
            double matchPercentage = trie.compareMatchPercentage(inputSequence);
          
            if (matchPercentage == 100) {
                maxMatchPercentage = 100;
                bestMatchSequence = storedSequence;
                break; 
            }
            
            if(matchPercentage > maxMatchPercentage) {
                maxMatchPercentage = matchPercentage;
                bestMatchSequence = storedSequence;
            }
        }
        
        if (bestMatchSequence != null){
            return new Response (maxMatchPercentage, bestMatchSequence.getName(), bestMatchSequence.getDescription(), bestMatchSequence.getLink());
        } else {
            return new Response(0, "Nenhuma correspondência", "", "");
        }
    }
}