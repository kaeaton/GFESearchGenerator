package org.chori.gsg.view.gfeSearchPanels;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides the hashmap containing loci names and Exon count for all loci
 * 
 * @author Katrina Eaton
 * 
 */
public class ExonCount {
	private static final Map<String, Integer> exonTotal = initiateExonCountHashMap();

	public ExonCount() { }

	public HashMap<String, Integer> getExonCount() {
		HashMap<String, Integer> totalExonsHashMap = new HashMap<String, Integer>(exonTotal);
		
		return totalExonsHashMap;
	}

	private static Map<String, Integer> initiateExonCountHashMap() {
		Map<String, Integer> map = new HashMap<>();

		map.put("HLA-A", 8);
		map.put("HLA-B", 7);
		map.put("HLA-C", 8);
		map.put("HLA-DPA1", 4);
		map.put("HLA-DPB1", 5);
		map.put("HLA-DQA1", 4);
		map.put("HLA-DQB1", 6);
		map.put("HLA-DRB1", 6);
		map.put("HLA-DRB3", 6);
		map.put("HLA-DRB4", 6);
		map.put("HLA-DRB5", 6);
		map.put("KIR2DL4", 9);   // missing Exon 4
		map.put("KIR2DL5A", 9);  // missing Exon 4
		map.put("KIR2DL5B", 9);  // missing Exon 4
		map.put("KIR2DP1", 9);
		map.put("KIR2DS1", 9);
		map.put("KIR2DS2", 9);
		map.put("KIR2DS3", 9);
		map.put("KIR2DS4", 9);
		map.put("KIR2DS5", 9);
		map.put("KIR3DL1", 9);
		map.put("KIR3DL2", 9);
		map.put("KIR3DL3", 9);  // missing Exon 6
		map.put("KIR3DP1", 5);  // Exon 2 = 0 bp?
		map.put("KIR3DS1", 9);

		return Collections.unmodifiableMap(map);
	}

}
