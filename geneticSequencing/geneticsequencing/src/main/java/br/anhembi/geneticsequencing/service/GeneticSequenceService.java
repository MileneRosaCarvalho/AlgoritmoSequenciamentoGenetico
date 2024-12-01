package br.anhembi.geneticsequencing.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.anhembi.geneticsequencing.list.MyList;
import br.anhembi.geneticsequencing.model.GeneticSequence;
import br.anhembi.geneticsequencing.repository.GeneticSequenceRepository;
import br.anhembi.geneticsequencing.trie.*;
import br.anhembi.geneticsequencing.model.dto.Response;

@Service //A anotação @Service indica que esta classe é um serviço em Spring, o que significa que ela contém a lógica de negócios e pode ser injetada em outros componentes (como controladores).
public class GeneticSequenceService {
    
    @Autowired //A anotação @Autowired é usada para injeção de dependência. Aqui, ela injeta automaticamente o repositório GeneticSequenceRepository, que provavelmente gerencia a persistência das sequências genéticas no banco de dados (com JPA).
    private GeneticSequenceRepository geneticSequenceRepository;

    public List <GeneticSequence> getAllSequences(){ //Recupera todas as sequências genéticas armazenadas no banco de dados e retorna como uma lista.
        MyList<GeneticSequence> listGeneticSequences = new MyList<>();
        List<GeneticSequence> storedGeneticSequences = geneticSequenceRepository.findAll(); //O método geneticSequenceRepository.findAll() recupera todas as sequências genéticas armazenadas no banco de dados.
        
        for (GeneticSequence seq : storedGeneticSequences) { //Para cada sequência genética obtida, ela é adicionada a uma instância de MyList.
            listGeneticSequences.add(seq);
        }
        return listGeneticSequences.allElements(); // é chamado para retornar todos os elementos da lista listGeneticSequences.
    }

    // Este método recebe uma sequência genética de entrada (inputSequence) e compara com todas as sequências armazenadas no banco de dados, 
    //calculando a porcentagem de correspondência entre elas e a sequência de entrada. Ele retorna o melhor resultado com a maior porcentagem de correspondência.
    public Response calculateMatchPercentage(String inputSequence){
        MyList<GeneticSequence> listGeneticSequences = new MyList<>();
        List<GeneticSequence> storedGeneticSequences = geneticSequenceRepository.findAll();

        for (GeneticSequence seq : storedGeneticSequences) {
            listGeneticSequences.add(seq);
        }

        double maxMatchPercentage = 0; //Armazena a maior porcentagem de correspondência encontrada.
        GeneticSequence bestMatchSequence = null; //Armazenar a sequência genética com a maior correspondência.

        //Para cada sequência genética armazenada, um objeto SuffixTrie é criado e a sequência genética é inserida nela.
        for (GeneticSequence storedSequence : listGeneticSequences.allElements()){
            SuffixTrie trie = new SuffixTrie();
            trie.insert(storedSequence.getSequence());

            //é chamado para calcular a porcentagem de correspondência entre a sequência armazenada e a sequência de entrada.
            double matchPercentage = trie.compareMatchPercentage(inputSequence);

            //Se a porcentagem de correspondência for 100%, o método interrompe a busca, pois já encontrou uma correspondência exata.
            if (matchPercentage == 100) {
                maxMatchPercentage = 100;
                bestMatchSequence = storedSequence;
                break; 
            }
            
            //Caso contrário, se a porcentagem de correspondência for maior que a maxMatchPercentage atual, o valor é atualizado.

            if(matchPercentage > maxMatchPercentage) {
                maxMatchPercentage = matchPercentage;
                bestMatchSequence = storedSequence;
            }
        }

        //Após comparar todas as sequências, o método retorna a sequência com a maior porcentagem de correspondência, usando a classe Response para retornar os dados relevantes, como o nome, descrição e link da sequência.
        if (bestMatchSequence != null){
            return new Response (maxMatchPercentage, bestMatchSequence.getName(), bestMatchSequence.getDescription(), bestMatchSequence.getLink());
        } else {
            return new Response(0, "Nenhuma correspondência", "", "");
        }
    }
}