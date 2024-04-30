package com.jsontocsv;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class JSONToCSVConvertorTest {

	@Test
    public void testConvertJSONArrayToCSV() throws IOException {
        // Specify the file paths for the test JSON file and the expected CSV file
        String inputPath = "/Users/sriganth/git/java-basics/jsontocsv/src/test/resources/input.json";
        String outputPath = "/Users/sriganth/git/java-basics/jsontocsv/src/test/resources/output.csv";
        String expectedPath = "/Users/sriganth/git/java-basics/jsontocsv/src/test/resources/expected.csv";

        // Convert the JSON array to CSV file
        JSONToCSVConvertor.convertJSONArrayToCSV(inputPath, outputPath);

        // Read the content of the expected and actual CSV files
        String expectedCSV = new String(Files.readAllBytes(Paths.get(expectedPath))).trim();
        String actualCSV = new String(Files.readAllBytes(Paths.get(outputPath))).trim();

        // Print the content of the expected and actual CSV files
        System.out.println("Expected CSV:");
        System.out.println(expectedCSV);
        System.out.println("Actual CSV:");
        System.out.println(actualCSV);

        // Compare the content of the expected and actual CSV files
        assertEquals(expectedCSV, actualCSV, "Generated CSV should match expected CSV");
    }
}
