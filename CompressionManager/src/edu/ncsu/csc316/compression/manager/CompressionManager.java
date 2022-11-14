package edu.ncsu.csc316.compression.manager;

import java.io.FileNotFoundException;
import edu.ncsu.csc316.compression.dsa.DSAFactory;
import edu.ncsu.csc316.compression.io.InputReader;
import edu.ncsu.csc316.dsa.data.Identifiable;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;


/**
 * The CompressionManager class gets compressed and decompressed files
 * and reads, then returns, maps that have compressed and decompressed
 * words from these files.
 * @author Jamel Clarke
 *
 */
public class CompressionManager {
	
	 /** Map that will be used to hold lines from input. */
    private Map<Integer, List<String>> map;
    
    /** Map that will hold each compressed word and its occurrence in the file. */
    private Map<String, Integer> compressedKeys;
    
    /** Map that will hold each word and its occurrence in the file. */
    private Map<Integer, String> dictionary;
    
	/**
	 * Constructs a CompressionManager object, given an input file, that
	 * will use InputReader.readFile() to read it in for use in
	 * CompressionManager.
	 * @param pathToInputFile file path to the input file
	 * @throws FileNotFoundException throws {@link FileNotFoundException}
	 *         if the file does not exist
	 */
    public CompressionManager (String pathToInputFile) throws FileNotFoundException {
    	try {
	    	//get the lines from file
	    	map = InputReader.readFile(pathToInputFile);
	    	
	    	
	    	//sort the lines
	    		//create the entry array and counter
	    	IdentifiableEntry[] entryArray = new IdentifiableEntry[map.size()];
	    	int counter = 0;
	    		//add each line to the array
	    	for ( Entry<Integer, List<String>> line : map.entrySet()) {
	    		entryArray[ counter++ ] = new IdentifiableEntry(line);
	    	}
	    		//sort it
	    	DSAFactory.getNonComparisonSorter().sort(entryArray);
	    	
	    	//clear the current map and add the sorted lines to it
	    	map = DSAFactory.getMap(null);
	    	for (int i = 0; i < entryArray.length; i++) {
	    		map.put(i, entryArray[i].getEntry().getValue());
	    	}
    	} catch ( FileNotFoundException fnfe ) {
    		throw new IllegalArgumentException("The input file could not be accessed.");
    	}
    }

    /**
     * Returns a map with the occurrence of each compressed word as the key
     * and the actual word as the value.
     * L: numLines
     * W: numWords
     * 
     * @return a map of compressed words from the input file
     */
    public Map<Integer, List<String>> getCompressed() {
    	if (map.size() == 0) {
    		throw new IllegalArgumentException("The provided input file has no text to compress.");
    	}
    	
    	int wordCount = 1;
    	compressedKeys = DSAFactory.getMap(null);
    	
    	for ( Entry<Integer, List<String>> line : map.entrySet()) {
    		for ( int i = 0; i < line.getValue().size(); i++) {
    			String word = line.getValue().remove(i);
    			try {
    				int key = compressedKeys.get(word);
    				line.getValue().addFirst("" + key);
    			} catch ( NullPointerException npe ) {
    				compressedKeys.put(word, wordCount);
    				line.getValue().addFirst(word);
    			}
    			wordCount++;
    		}
    	}
    	
    	return map;
    }

    /**
     * Returns a map with the occurrence of each decompressed word as the key
     * and the actual word as the value.
     * @return a map of decompressed words from the input file
     */
    public Map<Integer, List<String>> getDecompressed() {
    	if (map.size() == 0) {
    		throw new IllegalArgumentException("The provided input file has no text to decompress.");
    	}
    	
    	int wordCount = 1;
    	dictionary = DSAFactory.getMap(null);
		for ( Entry<Integer, List<String>> line : map.entrySet()) {
			for ( int i = 0; i < line.getValue().size(); i++) {
				String word = line.getValue().remove(i);
				dictionary.put(wordCount, word);
				if (Character.isDigit(word.charAt(0))) {
					int key = Integer.parseInt(dictionary.remove(wordCount));
					line.getValue().addFirst(dictionary.get(key));
				} else {
					line.getValue().addFirst(word);
				}
    			wordCount++;
			}
		}
		
		return map;
    }
    
    /**
     * Wrapper class used to hold entries so that they can be added to an array.
     * @author Jamel Clarke
     */
    private class IdentifiableEntry implements Identifiable {
    	
    	/** The actual entry being held by this wrapper. */
    	private Entry<Integer, List<String>> actualEntry;
    	
    	public IdentifiableEntry(Entry<Integer, List<String>> e) {
    		actualEntry = e;
    	}

		public Entry<Integer, List<String>> getEntry() {
			return actualEntry;
		}

		@Override
		public int getId() {
			return actualEntry.getKey();
		}
    }
}

