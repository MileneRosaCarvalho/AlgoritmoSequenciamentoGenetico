package br.anhembi.geneticsequencing.trie;

import java.util.HashMap; // Importa a classe HashMap, que será usada para armazenar filhos no nó da trie.
import java.util.Map; // Importa a interface Map, que é implementada por HashMap.

class SuffixTrieNode { // Define a classe SuffixTrieNode que representa um nó na trie, onde cada nó armazena um mapa de caracteres (children) e uma flag.
    Map <Character, SuffixTrieNode> children = new HashMap<>(); // Mapeia um caractere para outro nó da trie.
    boolean idEndOfSequence; // Indica se o nó é o fim de um sufixo.

    public SuffixTrieNode(){ // Construtor da classe.
        this.idEndOfSequence = false; // Inicializa idEndOfSequence como falso, Isso significa que, ao criar um nó, ele não será marcado como o fim de um sufixo.
    }
}

public class SuffixTrie { // Define a classe SuffixTrie que representa a trie completa que armazena todos os sufixos de uma sequência de caracteres.
    private SuffixTrieNode root; // A raiz da trie, orepresentada por um nó inicial.
    private int originalSequenceLength;  // Armazena o comprimento da sequência original inserida na trie.

    //Inicializa a trie criando um nó raiz. Esse nó será usado como ponto de partida para inserir todos os sufixos da sequência.
    public SuffixTrie(){ // Construtor da classe SuffixTrie
        this.root = new SuffixTrieNode(); // Inicializa a raiz da trie
    }

    public void insert(String sequence){ // Método para inserir todos os sufixos da sequência na trie.
        this.originalSequenceLength = sequence.length(); // Armazena o comprimento da sequência original
        for (int i = 0; i < sequence.length(); i++) { // Para cada posição i da sequência
            
            //Cada sufixo gerado é inserido na trie usando esse método.
            insertSuffix(sequence.substring(i)); // Insere o sufixo começando na posição i
        }
    }
  
    public void insertSuffix (String suffix){ // Método para inserir um único sufixo na trie.
        SuffixTrieNode currentNode = root; // Começa no nó raiz, Variável que começa na raiz da trie e percorre a trie enquanto insere os caracteres do sufixo.

        for (int i = 0; i < suffix.length(); i++) { // Para cada caractere no sufixo
            char currentChar = suffix.charAt(i); // Pega o caractere atual do sufixo
            currentNode.children.putIfAbsent(currentChar, new SuffixTrieNode()); //Se o caractere (currentChar) ainda não tiver um nó filho correspondente, cria-se um novo nó (new SuffixTrieNode()).
            currentNode = currentNode.children.get(currentChar); //Depois de verificar ou criar o nó, movemos o ponteiro currentNode para o próximo nó filho correspondente ao caractere.
        }
        currentNode.idEndOfSequence = true; //Marca o último nó do sufixo como o final de uma sequência, indicando que este nó representa o fim de um sufixo válido da sequência original.
    }

    public boolean search(String pattern){ //Este método busca se o padrão fornecido existe entre os sufixos armazenados na trie.
        SuffixTrieNode currentNode = root;

        for (int i = 0; i < pattern.length(); i++) {  // Para cada caractere do padrão
            char currentChar = pattern.charAt(i); // Pega o caractere atual do padrão
            if(!currentNode.children.containsKey(currentChar)){  //Verifica se o nó corrente tem um filho para o caractere atual. Se não tiver, retorna false.
                return false; 
            }
            currentNode = currentNode.children.get(currentChar); // Move para o próximo nó
        }
        return true; // Retorna verdadeiro se o padrão foi encontrado.
    }
  
    public double compareMatchPercentage(String pattern){ // Método para comparar o padrão fornecido com os sufixos armazenados na trie e calcular a porcentagem de correspondência.
        SuffixTrieNode currentNode = root;
        int matchCount = 0; // Contador que mantém o número de caracteres que correspondem entre o padrão e os sufixos.

        for (int i = 0; i < pattern.length(); i++) {
            char currentChar = pattern.charAt(i);
            
            if(!currentNode.children.containsKey(currentChar)){
                break; 
            }
            currentNode = currentNode.children.get(currentChar);
            matchCount++; // Incrementa o contador de correspondências
        }

        if(matchCount == pattern.length()) {  // Se todos os caracteres do padrão foram correspondidos
            return 100.0; //Retorna 100% de correspondência
        }

        //Se a correspondência não for completa, calcula a porcentagem de correspondência com base no número de caracteres correspondentes (matchCount) e o comprimento da sequência original.
        return (matchCount / (double) originalSequenceLength) * 100;
    }

    public int getOriginalSequenceLength() { // Método para obter o comprimento da sequência original.
        return originalSequenceLength;
    }
}