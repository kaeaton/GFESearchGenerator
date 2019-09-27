package org.chori.bsg.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.*;

/**
 * used this how to:
 * https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
 **/

public class SortData {

	public SortData() { }

	public LinkedHashMap<String, String> sortTheData(HashMap<String, String> toBeSorted) {
		
		// temp and final linked hashmaps to hold the order of the data
		LinkedHashMap<String, String> sortedData = new LinkedHashMap();
		LinkedHashMap<String, String> intermediateDataStore = new LinkedHashMap();

        String tempString = new String();
		String[] temp = new String[2];
		String tempGfe = new String();

		// Regex pattern for removing end letters
		String pattern = "(^.+)([A-Z]+)";
		Pattern r = Pattern.compile(pattern);

		

		// cycle through provided hashmap
		for (Map.Entry me:toBeSorted.entrySet()) {
			
			// get name (value), split into locus designation & numeric portion
            tempString = (String)me.getValue();
            temp = tempString.split("\\*");

            // store value to reassociate with processed key
            tempGfe = (String)me.getKey();

            // if there are letter designations at the end, remove them
            // (they screw up the sorting)
			Matcher m = r.matcher(temp[1]);
            if (m.find()) {
            	temp[1] = m.group(1);
            }

			// split name on colons
            String[] separated = temp[1].split(":");
            String reassembledString = "";

			// add zeros
            for(int i = 0; i < separated.length; i++) { 
				String paddedString = StringUtils.leftPad(separated[i], 4, "0");
				reassembledString = (reassembledString + paddedString + ":");
			}

			// gfe as key, padded name as value
			intermediateDataStore.put(tempGfe, reassembledString);
		}

		// get a list of values from intermediateDataStore
		List<Map.Entry<String, String> > sortedList = 
               new LinkedList<Map.Entry<String, String> >(intermediateDataStore.entrySet());
    	System.out.println("Reached Sort the data");

		// Sort the list 
        Collections.sort(sortedList, new Comparator<Map.Entry<String, String> >() { 
            public int compare(Map.Entry<String, String> o1,  
                               Map.Entry<String, String> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 

        // put data from sorted list to hashmap  
        for (Map.Entry<String, String> aa : sortedList) { 
        	String originalName = toBeSorted.get(aa.getKey());
            sortedData.put(aa.getKey(), originalName); 
        } 
        return sortedData;
	}
}