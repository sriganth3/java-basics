package com.jsontocsv;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

public class JSONToCSVConvertorTest {

    @Test
    public void testConvertJSONArrayToCSV() throws IOException {

        String inputPath = "/Users/sriganth/git/java-basics/jsontocsv/src/test/resources/input.json";
        String outputPath = "/Users/sriganth/git/java-basics/jsontocsv/src/test/resources/output.csv";
        String expectedPath = "/Users/sriganth/git/java-basics/jsontocsv/src/test/resources/expected.csv";


        JSONToCSVConvertor.convertJSONArrayToCSV(inputPath, outputPath);

        String expectedCSV = new String(Files.readAllBytes(Paths.get(expectedPath))).trim();
        String actualCSV = new String(Files.readAllBytes(Paths.get(outputPath))).trim();


        expectedCSV = sortCSV(expectedCSV);
        actualCSV = sortCSV(actualCSV);

        System.out.println("Expected CSV:");
        System.out.println(expectedCSV);
        System.out.println("Actual CSV:");
        System.out.println(actualCSV);
        System.out.println("End");


        assertEquals("Generated CSV should match expected CSV", expectedCSV, actualCSV);
    }

    private String sortCSV(String csv) {
        String[] lines = csv.split("\\r?\\n");
        String header = lines[0];
        String[] dataLines = Arrays.copyOfRange(lines, 1, lines.length);
        Arrays.sort(dataLines); 
        StringBuilder sortedCSV = new StringBuilder(header).append("\n");
        for (String line : dataLines) {
            sortedCSV.append(line).append("\n");
        }
        return sortedCSV.toString().trim();
    }
}
