package com.cochrane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class URLHandler {

	static int topicIndex = 0; // Keeps track of the current topic index
	static Connection.Response loginForm; // Stores the initial session cookies

	/**
	 * Initiates a session by establishing a connection to the main page and login page.
	 *
	 * @return Document representing the evaluation page or null if an exception occurs.
	 */
	public static Document intiateSession() {
		String LoginUrl = "https://www.cochranelibrary.com/c/portal/login";
		String mainPageUrl = "http://www.cochranelibrary.com/";

		try {
			// Obtain cookies from the main page
			loginForm = Jsoup.connect(mainPageUrl)
					.method(Connection.Method.GET)
					.execute();

			// Access the login page using the cookies
			Connection.Response mainPage = Jsoup.connect(LoginUrl)
					.cookies(loginForm.cookies())
					.execute();

			// Extract cookies for further requests
			Map<String, String> cookies = mainPage.cookies();

			// Pause execution briefly to prevent overloading the server
			Thread.sleep(1000);

			// Load and return the evaluation page
			return Jsoup.connect(mainPageUrl)
					.cookies(cookies)
					.execute()
					.parse();

		} catch (IOException | InterruptedException e) {
			return null; // Return null if an exception occurs
		}
	}

	/**
	 * Fetches a specific topic element from the home page.
	 *
	 * @param homePage Document representing the home page.
	 * @return The selected topic element or null if no more topics are available.
	 */
	public static Element getTopic(Document homePage) {
		Elements topics = homePage.getElementsByClass("browse-by-list-item");

		if (topicIndex < topics.size()) {
			Element topic = topics.get(topicIndex);
			topicIndex++;
			return topic;
		}

		return null; // Return null if all topics are processed
	}

	/**
	 * Retrieves the reviews page for the given topic URL.
	 *
	 * @param topicUrl The URL of the topic's reviews page.
	 * @return Document representing the reviews page or null if an error occurs.
	 */
	public static Document getReviewsPage(String topicUrl) {
		try {
			// Pause execution briefly
			Thread.sleep(1000);

			// Use session cookies to access the reviews page
			return Jsoup.connect(topicUrl)
					.cookies(loginForm.cookies())
					.execute()
					.parse();

		} catch (InterruptedException | IOException e) {
			return null; // Return null if an exception occurs
		}
	}

	/**
	 * Extracts URLs of all next pages from the reviews page.
	 *
	 * @param reviewPage Document representing the reviews page.
	 * @return ArrayList containing URLs of the next pages.
	 */
	public static ArrayList<String> getNextPagesUrl(Document reviewPage) {
		ArrayList<String> nextPages = new ArrayList<>();

		// Locate pagination elements
		Elements allNextPages = reviewPage.getElementsByClass("pagination-page-list-item");

		for (Element page : allNextPages) {
			// Add the href attribute of each page link to the list
			nextPages.add(page.select("a[href]").attr("href"));
		}

		return nextPages;
	}
}
