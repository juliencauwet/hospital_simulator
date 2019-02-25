package org.hospital.simulator;

import edu.princeton.cs.introcs.StdOut;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulatorApplication.class, args);

		Map<String, Integer> results;
		String [] drugs = {};

		//checks if all args are valid
		if(checkNoWrongArgs(args)) {

			//splits 1st string arg and inserts into an array of patients
			String[] patients = args[0].split(",");

			//splits 2nd string arg (if exists) and inserts into an array of drugs
			if (args.length >= 2)
				drugs = args[1].split(",");

			//call getResultMap to map number of patients to different states
			results = getResultMap(patients, drugs);

			//call display method to display the results
			display(results);

		}else {
			StdOut.println("Input parameters are not correct.");
		}
	}

	/**
	 * analyzes the final state according to initial state and drugs administered
	 * @param initialState
	 * @param drugs
	 * @return patient's state after treatment
	 */
	public static char stateAfterTreatment(char initialState, String [] drugs){

		// any + As & P --> X  if both Aspirine & Paracetamol are administered, patients die
		if (Arrays.asList(drugs).contains("As") && Arrays.asList(drugs).contains("P"))
			return 'X';

		// H +I & An --> F if both Insuline and Antibiotics are administered, healthy patient gets fiever
		if (initialState == 'H' && Arrays.asList(drugs).contains("I") && Arrays.asList(drugs).contains("An"))
			return 'F';

		// F + As || P --> H if a patient with fiever is administered with Aspirin or Paracetamol, he's healed
		if (initialState == 'F' && (Arrays.asList(drugs).contains("As") || Arrays.asList(drugs).contains("P")))
			return 'H';

		// T + An --> H  if a tuberculosis patient is administered with Antibiotics, he's healed
		if (initialState == 'T' && Arrays.asList(drugs).contains("An") )
			return 'H';

		// D + !I --> X if a diabetic patient does not get any insuline, he will die
		if (initialState == 'D' && !Arrays.asList(drugs).contains("I"))
			return 'X';

		// X * 1/1M --> H Miracle happens
		if( initialState == 'X' && (int)(Math.random() * Math.pow(10,6)) + 1 == 1)
			return 'H';

		return initialState;
	}

	/**
	 * initializes the map and calls stateAfterTreatmant for each patient. Gets the results and inserts into a map
	 * @param patients
	 * @param drugs
	 * @return the map of the results
	 */
	public static Map <String, Integer> getResultMap(String [] patients, String [] drugs){
		Map<String, Integer> results = new HashMap<>();
		results.put("F", 0);
		results.put("H", 0);
		results.put("D", 0);
		results.put("T", 0);
		results.put("X", 0);

		String state;

		//for each patient, process drug treatment filter and insert into results
		for (int i = 0 ; i < patients.length ; i++){
			state = Character.toString(stateAfterTreatment(patients[i].charAt(0), drugs));

			results.put(state, results.get(state) + 1);
		}
		return results;
	}

	/**
	 * displays the results in the console via StdOut
	 * @param results
	 */
	public static void display(Map<String, Integer> results){
		String display = "";

		//builds the display string
		display += "F:" + results.get("F") +",";
		display += "H:" + results.get("H") +",";
		display += "D:" + results.get("D") +",";
		display += "T:" + results.get("T") +",";
		display += "X:" + results.get("X");

		//displays the result
		StdOut.println("###################");
		StdOut.println(display);
		StdOut.println("###################");
	}

	/**
	 * checks if all args passed as parameters are valid (all patients and drugs exist)
	 * @param args
	 * @return true or false
	 */
	public static Boolean checkNoWrongArgs(String [] args){
		if(args.length < 1)
			return false;

		String [] states = {"X", "H", "D", "T", "F"};
		String [] possibleDrugs = {"I", "An", "As", "P"};

		String[] patients = args[0].split(",");
		String[] drugs;

		//Loops over patients args to check if they all exist
		for(int i = 0; i < patients.length; i++) {
			if (!Arrays.asList(states).contains(patients[i]))
				return false;
		}

		//Loops over drugs args to check if they all exist
		if (args.length >= 2) {
			drugs = args[1].split(",");
			for(int i = 0; i < drugs.length; i++) {
				if (!Arrays.asList(possibleDrugs).contains(drugs[i]))
					return false;
			}
		}
		return true;
	}



}
