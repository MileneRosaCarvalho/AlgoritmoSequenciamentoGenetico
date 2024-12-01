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

//Controller da aplicação Spring Boot. Ele é responsável por expor uma API REST para que os clientes possam interagir com o serviço.
//@RequestMapping, @GetMapping, @RequestParam: Anotações do Spring MVC que definem rotas HTTP e parâmetros de entrada.

@RestController 
@RequestMapping("/api/sequences")
@CrossOrigin("*") //Permite o acesso cross-origin, permitindo que outras origens (domínios) possam fazer requisições para esse controlador. O valor * indica que é permitido qualquer domínio.
public class GeneticSequenceController {
    
    @Autowired //A anotação é usada para injetar automaticamente a dependência do GeneticSequenceService no controlador. Isso significa que o Spring Boot irá automaticamente criar uma instância do GeneticSequenceService e injetá-la no controlador para ser utilizada.
    private GeneticSequenceService sequenceService;
    
    //Define que este método irá responder a requisições HTTP GET para a URL /api/sequences/compare.
    @GetMapping("/compare")

    //Este é o método responsável por comparar a sequência genética recebida na requisição com as sequências armazenadas no banco de dados, calculando a porcentagem de correspondência.
    //A anotação @RequestParam indica que o valor de inputSequence será obtido a partir dos parâmetros da requisição GET.
    public ResponseEntity <Response> compareSequence(@RequestParam("inputSequence") String inputSequence) {
      
      // Esse método chama o serviço GeneticSequenceService, especificamente o método calculateMatchPercentage, passando a sequência genética de entrada. 
      //Este método retorna um objeto Response, que contém os resultados da comparação
      Response response = sequenceService.calculateMatchPercentage(inputSequence);

        // Retorna a resposta HTTP com o status 200 OK e o corpo da resposta contendo o objeto response
        return ResponseEntity.ok(response);
    }
}