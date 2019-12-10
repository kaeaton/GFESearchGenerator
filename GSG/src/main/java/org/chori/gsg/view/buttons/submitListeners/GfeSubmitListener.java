// package org.chori.gsg.view.buttons.submitListeners;


// import org.chori.gsg.controller.*;
// import org.chori.gsg.model.*;
// import org.chori.gsg.view.*;
// import org.chori.gsg.view.buttons.*;
// import org.chori.gsg.view.searchboxes.*;

// public class GfeSubmitListener {




// 	public ActionListener hlaListener = new ActionListener() {
// 		@Override
// 		public void actionPerformed(ActionEvent evt) {
// 			Runnable submit = new Runnable() {
// 				public void run() {

// 					// the lists of hla components
// 					ArrayList<JTextField> allTextFields = HlaSearchBoxAssembler.allTextboxes;
// 					ArrayList<JCheckBox> allCheckBoxes = HlaSearchBoxAssembler.allCheckboxes;

// 					// what locus, version, and format?
// 					String whatLocus = B12xGui.whatLocusHla.getSelectedItem().toString();
// 					String whatVersion = B12xGui.whatVersionHla.getSelectedItem().toString();
// 					String dataFormat = dataFormatFinder(B12xGui.fileFormatHla);
// 					Boolean printToFile = printToFileFinder(B12xGui.fileFormatHla);
// 					System.out.println(whatLocus + ", " + whatVersion + ", " + dataFormat + ", " + printToFile);

// 					// where's the data file?                 
// 					File data = wtdl.getRawData(whatLocus, whatVersion);

// 					// build me some Regex
// 					String regex = buildRegex.assembleHlaGfeRegex("HLA", whatLocus, 
// 															allCheckBoxes, allTextFields);
// 					String headerSS = buildHSS.assembleHlaHeaderSearchString("HLA", whatLocus, 
// 															allCheckBoxes, allTextFields);

// 					// clear results screen
// 					B12xGui.resultsTextAreaHla.setText("");

// 					// print headers
// 					header.printHeaders("HLA", headerSS, whatVersion, whatLocus, dataSources.get("neo4j"));
					
// 					// search the data & print to screen
// 					if (dataFormat.equals("Pretty")) {
// 						PrettyData prettyData = new PrettyData();
// 						prettyData.searchThroughData(data, regex, "HLA");
// 					} else {
// 						SearchData searchData = new SearchData();
// 						searchData.searchThroughData(data, regex, dataFormat, "HLA");
// 					}

// 					if (printToFile) {
// 						WriteToFile writeToFile = new WriteToFile();
// 						writeToFile.writeFile(whatLocus, whatVersion, "HLA", dataFormat);
// 					}
// 				}
// 			};
// 			new Thread(submit).start();

// 		}
// 	};
// }