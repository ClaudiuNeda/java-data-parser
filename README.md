# ☕ Java Data Parser

A Java application developed to demonstrate the extraction, processing, and aggregation of data from multiple sources and different formats.

## 🛠️ Technologies and Concepts Used
* **Java 8+ (Streams API):** Advanced data filtering, mapping, and aggregation (`Collectors.groupingBy`).
* **JDBC (Java Database Connectivity):** Connecting to and querying relational databases (SQLite).
* **JSON Parsing:** Extracting structured data using the `org.json` library.
* **File I/O:** Efficient reading and parsing of Text/CSV files.

## ⚙️ How it Works
The project integrates 3 different data sources:
1. **Relational Databases (.db):** Reads patient information through SQL queries (JDBC).
2. **JSON Files (.json):** Extracts department details and filters them dynamically based on capacity.
3. **CSV/Text Files (.txt):** Parses stock market volumes and calculates maximum/minimum values using custom comparators.

The application correlates the data (e.g., calculates the number of patients per department by matching database records with JSON data) and generates clean, formatted reports directly in the console.

## 🚀 How to Run (Local Setup)

### Prerequisites
* Java JDK 8 or higher
* IntelliJ IDEA (recommended)

### Steps
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/ClaudiuNeda/java-data-parser.git](https://github.com/ClaudiuNeda/java-data-parser.git)
   ```

2. **Open the project:**
   Open the cloned folder in IntelliJ IDEA.


3. **Configure Dependencies (SQLite & JSON):**
    * Go to `File` ➔ `Project Structure` ➔ `Modules` ➔ `Dependencies` tab.
    * Click the `+` icon at the bottom/side, select `Library...` ➔ `From Maven...`.
    * Search for and add: `org.json:json:20190722` (for JSON parsing).
    * Search for and add: `org.xerial:sqlite-jdbc:3.30.1` (for the SQLite database).
    * *(Alternatively, you can add the SQLite driver directly from the `lib/` folder using `JARs or directories...`)*
   

4. **Run the application:**
    * Navigate to `src/Main.java`.
    * Click the green play button next to the `public static void main` method to execute the code.