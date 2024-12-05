package br.anhembi.geneticsequencing.trie;

import java.util.HashMap; 
import java.util.Map;

class SuffixTrieNode { 
    Map <Character, SuffixTrieNode> children = new HashMap<>(); 
    boolean idEndOfSequence; 

    public SuffixTrieNode(){ 
        this.idEndOfSequence = false; 
    }
}

public class SuffixTrie { 
    private SuffixTrieNode root; 
    private int originalSequenceLength;  


    public SuffixTrie(){ 
        this.root = new SuffixTrieNode(); 
    }

    public void insert(String sequence){ 
        this.originalSequenceLength = sequence.length(); 
        for (int i = 0; i < sequence.length(); i++) { 
            
            
            insertSuffix(sequence.substring(i)); 
        }
    }
  
    public void insertSuffix (String suffix){ 
        SuffixTrieNode currentNode = root; 

        for (int i = 0; i < suffix.length(); i++) { 
            char currentChar = suffix.charAt(i); 
            currentNode.children.putIfAbsent(currentChar, new SuffixTrieNode()); 
            currentNode = currentNode.children.get(currentChar); 
        }
        currentNode.idEndOfSequence = true; 
    }

    public boolean search(String pattern){ 
        SuffixTrieNode currentNode = root;

        for (int i = 0; i < pattern.length(); i++) {  
            char currentChar = pattern.charAt(i); 
            if(!currentNode.children.containsKey(currentChar)){ 
                return false; 
            }
            currentNode = currentNode.children.get(currentChar); 
        }
        return true; 
    }
  
    public double compareMatchPercentage(String pattern){ 
        SuffixTrieNode currentNode = root;
        int matchCount = 0; 

        for (int i = 0; i < pattern.length(); i++) {
            char currentChar = pattern.charAt(i);
            
            if(!currentNode.children.containsKey(currentChar)){
                break; 
            }
            currentNode = currentNode.children.get(currentChar);
            matchCount++; 
        }

        if(matchCount == pattern.length()) {  
            return 100.0; 
        }

        
        return (matchCount / (double) originalSequenceLength) * 100;
    }

    public int getOriginalSequenceLength() { 
        return originalSequenceLength;
    }
}