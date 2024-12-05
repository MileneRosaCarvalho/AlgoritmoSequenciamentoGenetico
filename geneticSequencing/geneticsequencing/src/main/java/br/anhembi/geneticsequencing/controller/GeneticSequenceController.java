package br.anhembi.geneticsequencing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import br.anhembi.geneticsequencing.model.dto.Response;
import br.anhembi.geneticsequencing.service.GeneticSequenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; 


@RestController 
@RequestMapping("/api/sequences")
@CrossOrigin("*") 
public class GeneticSequenceController {
    
    @Autowired 
    private GeneticSequenceService sequenceService;
    
    @GetMapping("/compare")
    public ResponseEntity <Response> compareSequence(@RequestParam("inputSequence") String inputSequence) {
      Response response = sequenceService.calculateMatchPercentage(inputSequence);
        return ResponseEntity.ok(response);
    }
}