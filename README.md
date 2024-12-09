# Cochrane Library Web Crawler

## Overview
The Cochrane Library Web Crawler is designed to efficiently gather and store review metadata from the Cochrane Library. It navigates the website, extracts the required data, and writes it to an output file for further use.

---

## Application Flow

1. **Initiate session and obtain cookies**  
   Establish a session with the Cochrane Library and retrieve cookies to enable seamless navigation.

2. **Retrieve the first topic from the homepage**  
   Identify the first topic listed on the Cochrane Library homepage.

3. **Fetch the first review page for the topic**  
   Access the first page containing reviews for the selected topic.

4. **Gather URLs of all review pages for the topic**  
   Collect all relevant URLs for review pages under the topic.

5. **Collect all reviews from the gathered pages**  
   Scrape the metadata for all reviews listed on the gathered pages.

6. **Write reviews to a text file**  
   Save the extracted review metadata into a structured text file for storage and analysis.

---

## Additional Features

### Potential Future Enhancements
The following features could be added to enrich the metadata collected:

- **Review Type**: Classify reviews (e.g., Systematic Review).  
- **Review Status**: Include review states (e.g., New, Updated, Withdrawn).  
- **Review Abstract or Summary**: Capture a brief overview of each review.

These additions could provide more comprehensive metadata but were excluded initially to maintain the scope of the project.

---

## Design Reasoning

The crawler focuses on efficient data extraction and storage while adhering to the following principles:

- **Scalability**: Handles multiple topics and reviews seamlessly.
- **Simplicity**: Avoids unnecessary complexity to maintain clarity and reliability.
- **Future-Proofing**: Room for easy extension of functionality.

---

## Code Dependencies

The project relies on the following libraries:

1. **JSoup (1.18.3 or later)**:  
   For web scraping and DOM parsing.  

   - [Download JSoup JAR file](https://jsoup.org/download)

2. **JSON-java (Latest stable version)**:  
   For working with JSON data.  

   - [Download JSON-java ZIP file](https://github.com/stleary/JSON-java)

---

## Adding Dependencies

To integrate the required dependencies:

1. Download the **JSoup JAR file** from the [official website](https://jsoup.org/download).
2. Download the **JSON-java ZIP file** from the [official GitHub repository](https://github.com/stleary/JSON-java).
3. Add the downloaded JAR files to your project's classpath or build configuration.

---

## Summary

This Cochrane Library Web Crawler efficiently gathers key metadata for reviews while leaving room for future enhancements. With its robust design and streamlined workflow, it is a valuable tool for extracting Cochrane Library data.

---

**Author**: *Jayanth Somyuri*
