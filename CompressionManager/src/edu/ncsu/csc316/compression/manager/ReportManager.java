/**
 * 
 */
package edu.ncsu.csc316.compression.manager;

import java.io.FileNotFoundException;
import edu.ncsu.csc316.compression.dsa.Algorithm;
import edu.ncsu.csc316.compression.dsa.DSAFactory;
import edu.ncsu.csc316.compression.dsa.DataStructure;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * The ReportManager class compresses and decompresses input text
 * from a file and returns it.
 * @author Jamel Clarke
 *
 */
public class ReportManager {

	/** The indent that will be used for compressing. */
    private static final String INDENT = "   ";

    /** The CompressionManager that will be used for getting lines of input. */
    private CompressionManager cm;
    /**
     * Constructs a ReportManager object that will be used by the GUI
     * to compress and decompress files
     * @param pathToInputFile the file path to the input file
     * @throws FileNotFoundException if the file cannot be found
     */
    public ReportManager(String pathToInputFile) throws FileNotFoundException {
    	DSAFactory.setMapType(DataStructure.SEARCHTABLE);
    	DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
    	DSAFactory.setComparisonSorterType(Algorithm.QUICKSORT);
    	DSAFactory.setNonComparisonSorterType(Algorithm.RADIX_SORT);
    	cm = new CompressionManager(pathToInputFile);
    	
		
    }

    /**
     * Returns compressed lines from text by replacing recurring words
     * with the numbers.
     * @return compressed lines from text by replacing recurring words
     *         with the number
     */
    public String compress() {
    	
    	
		Map<Integer, List<String>> compMap = cm.getCompressed();
		String compStr = "Compressed Output {\n";
		for ( Entry<Integer, List<String>> e : compMap.entrySet()) {
			compStr += INDENT + "Line " + ( e.getKey() + 1 ) + ": ";
			for ( int i = e.getValue().size() - 1; i >= 0; i--) {
				compStr += e.getValue().get(i);
				if ( i > 0 ) compStr += " "; 
			}
			compStr += "\n";
		}
		compStr += "}";
		return compStr;
    }
    
    /**
     * Returns decompressed lines from text by replacing numbers
     * with their corresponding words.
     * @return decompressed lines from text by replacing numbers
     *         with their corresponding words.
     */
    public String decompress() {
    	Map<Integer, List<String>> decompMap = cm.getDecompressed();
		String decompStr = "Decompressed Output {\n";
		for ( Entry<Integer, List<String>> e : decompMap.entrySet()) {
			decompStr += INDENT + "Line " + ( e.getKey() + 1 ) + ": ";
			for ( int i = e.getValue().size() - 1; i >= 0; i--) {
				decompStr += e.getValue().get(i);
				if ( i > 0 ) decompStr += " "; 
			}
			decompStr += "\n";
		}
		decompStr += "}";
		return decompStr;
    }
    

}
