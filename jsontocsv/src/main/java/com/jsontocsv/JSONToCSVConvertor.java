package com.jsontocsv;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONToCSVConvertor {

	public static void convertJSONArrayToCSV(String inputPath, String outputPath) throws IOException {

		JSONArray jsonArray = readJSONArrayFromFile(inputPath);
        try (FileWriter fileWriter = new FileWriter(outputPath);
        		
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {

            JSONObject firstObject = jsonArray.getJSONObject(0);
            firstObject.keySet().forEach(key -> {
                try {
                    csvPrinter.print(key);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            csvPrinter.println();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                jsonObject.keySet().forEach(key -> {
                    try {
                        csvPrinter.print(jsonObject.get(key).toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                csvPrinter.println();
            }

            csvPrinter.flush(); 
            System.out.println("CSV file saved successfully at: " + outputPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static JSONArray readJSONArrayFromFile(String inputPath) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(inputPath)));

        JSONArray jsonArray = new JSONArray(content);
        return jsonArray;
	}

}
