package com.findip;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HostToIPToCSV {
	public static void main(String[] args) {

		String inputFile = getResourceFilePath("hostnames.txt");
		String outputFile = "src/main/resources/IP_Addresses.csv";

		if (inputFile == null || outputFile == null) {
			System.out.println("Resource folder path not found!");
			return;
		}

		try (BufferedReader br = Files.newBufferedReader(Paths.get(inputFile));
				BufferedWriter bw = Files.newBufferedWriter(Paths.get(outputFile))) {

			bw.write("Hostname,IP Address");
			bw.newLine();

			String line;
			while ((line = br.readLine()) != null) {
				try {
					InetAddress inetAddress = InetAddress.getByName(line.trim());
					String ipAddress = inetAddress.getHostAddress();

					bw.write(line + "," + ipAddress);
					bw.newLine();
				} catch (UnknownHostException e) {
					System.out.println("Unable to resolve IP for: " + line);
					bw.write(line + ",Unknown Host");
					bw.newLine();
				}
			}

			System.out.println("IP Addresses have been written to: " + outputFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getResourceFilePath(String fileName) {
		try {
			Path resourcePath = Paths.get(
					HostToIPToCSV.class.getClassLoader().getResource("").toURI()
					).resolve(fileName);
			return resourcePath.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
