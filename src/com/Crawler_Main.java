package com.cochrane;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler_Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Execution Started");

        // Step 1: Initiate session and obtain cookies
        Document homePage = URLHandler.intiateSession();

        if (homePage == null) {
            System.out.println("Failed to initiate session.");
            return;
        }

        // Step 2: Retrieve the first topic from the home page
        Element topic = URLHandler.getTopic(homePage);
        if (topic == null) {
            System.out.println("No topics found.");
            return;
        }

        // Step 3: Fetch the first review page for the topic
        String topicUrl = topic.select("a[href]").attr("href");
        Document reviewPage = URLHandler.getReviewsPage(topicUrl);
        if (reviewPage == null) {
            System.out.println("Failed to fetch the review page.");
            return;
        }

        // Step 4: Gather URLs of all review pages for the topic
        ArrayList<String> allReviewPages = URLHandler.getNextPagesUrl(reviewPage);

        // Step 5: Collect all reviews from the gathered pages
        ArrayList<Elements> allReviews = new ArrayList<>();
        for (String pageUrl : allReviewPages) {
            Document page = URLHandler.getReviewsPage(pageUrl);
            if (page != null) {
                Elements reviews = page.getElementsByClass("search-results-item-body");
                allReviews.add(reviews);
            }
        }

        // Step 6: Write reviews to a text file
        boolean isFileCreated = FileHandler.createFile(allReviews, topic.text());
        if (isFileCreated) {
            System.out.println("Done");
        } else {
            System.out.println("Failed to write reviews to file.");
        }
    }
}
