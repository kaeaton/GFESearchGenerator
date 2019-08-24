package org.chori.bsg.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.*;



public class SortData {

	public SortData() { }

	public LinkedHashMap<String, String> sortTheData(HashMap<String, String> toBeSorted) {
		LinkedHashMap<String, String> sortedData = new LinkedHashMap();
		LinkedHashMap<String, String> intermediateDataStore = new LinkedHashMap();

		// get an entry from the hashmap (doesn't matter what)
		Map.Entry<String,String> entry = toBeSorted.entrySet().iterator().next();
 		String value = entry.getValue();

		// determine which side is GFE
		// pretty data comes through with GFE as key
		// other data may be variable
		int gfe = 1;
		if(value.contains("*")) { gfe = 0; }

		System.out.println("identified the GFE in SortData");

        String tempString = new String();
		String[] temp = new String[2];

		for (Map.Entry me:toBeSorted.entrySet()) {
			System.out.println("Made it to the toBeSorted loop in SortData");
			// get name (value)
			if(gfe == 0) {
                tempString = (String)me.getValue();
                System.out.println("Should be Name in SortData: " + tempString);
                temp = tempString.split("\\*");
            } else {
                tempString = (String)me.getKey();
                System.out.println("Should be Name in SortData: " + tempString);
                temp = tempString.split("\\*");
            }

			// split locus and name on asterisk
			// String[] holdingArray = me.split("*");

			// split name on colons
            String[] separated = temp[1].split(":");
            String reassembledString = separated[0];
			// add zeros
            for(int i = 1; i < separated.length; i++) { //separated[i]
				String paddedString = StringUtils.leftPad("te", 4, "0");
				reassembledString = (reassembledString + paddedString + ":");
				System.out.println(reassembledString);
			}
		}

		// get a list of values
		List<Map.Entry<String, String> > list = 
               new LinkedList<Map.Entry<String, String> >(toBeSorted.entrySet());
    	System.out.println("Reached Sort the data");

		// Sort the list 
        // Collections.sort(list, new Comparator<Map.Entry<String, String> >() { 
        //     public int compare(Map.Entry<String, String> o1,  
        //                        Map.Entry<String, String> o2) 
        //     { 
        //     	if(gfe1 == 0) {
	       //          return (o1.getValue()).compareTo(o2.getValue()); 
	       //      } else {
	       //          return (o1.getKey()).compareTo(o2.getKey()); 
	       //      }
        //     } 
        // }); 

        // put data from sorted list to hashmap  
        LinkedHashMap<String, String> sortedHash = new LinkedHashMap<String, String>(); 
        for (Map.Entry<String, String> aa : list) { 
            sortedHash.put(aa.getKey(), aa.getValue()); 
        } 
        return sortedHash;
	}
}