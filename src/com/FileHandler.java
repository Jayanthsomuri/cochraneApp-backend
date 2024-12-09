package com.cochrane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FileHandler {

	public static boolean createFile(ArrayList<Elements> reviews, String topic) {
		// Define the base URL and file path
		String baseURL = "https://www.cochranelibrary.com";
		String filePath = "output/cochrane_reviews.txt";

		// Attempt to write the reviews to the file
		try (FileWriter fileWriter = new FileWriter(filePath);
			 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

			// Iterate through each collection of reviews
			for (Elements reviewGroup : reviews) {
				for (Element review : reviewGroup) {
					// Construct the review details
					String reviewDetails = baseURL + review.getElementsByClass("result-title").select("a[href]").attr("href") + "|"
							+ topic + "|"
							+ review.getElementsByClass("result-title").text() + "|"
							+ review.getElementsByClass("search-result-authors").text() + "|"
							+ review.getElementsByClass("search-result-date").text();

					// Write the review details to the file
					bufferedWriter.write(reviewDetails);
					bufferedWriter.newLine();
				}
			}

			// Ensure all data is written
			fileWriter.flush();

		} catch (IOException e) {
			// Handle file operation exceptions
			System.err.println("Error writing to file: " + e.getMessage());
			return false;
		}

		// Indicate successful operation
		return true;
	}
}
