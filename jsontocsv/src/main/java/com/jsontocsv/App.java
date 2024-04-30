package com.jsontocsv;

import java.io.IOException;

public class App {
    public static void main( String[] args ){
    	
        // Specify the file path where CSV should be saved
        String inputPath = "/Users/sriganth/git/java-basics/jsontocsv/src/main/resources/students.json";
        String outputPath = "/Users/sriganth/git/java-basics/jsontocsv/src/main/resources/output.csv";
        
        JSONToCSVConvertor jsonToCsvConvertor = new JSONToCSVConvertor();

        try {
			jsonToCsvConvertor.convertJSONArrayToCSV(inputPath, outputPath);
		} catch (IOException e) {
			System.out.print("Error while converting the json to string: ");
			e.printStackTrace();
		}

    }
}
