##ade restaurant Transactions Application. 
A Java console-based application that helps users record, save, and manage financial transactions.

##Features

Add deposits and make payments.

View transaction history.

Search transactions.

Generate monthly and yearly reports.

Save transactions in a CSV file.

##Technologies Used

Java

ArrayList

File Handling (CSV)

Object-Oriented Programming (OOP)

##How It Works
 Home Page

Displays the main menu, allowing users to navigate to the Home Screen, Ledger, Help Menu, or exit the application.
 Home Screen

Allows users to add deposits, make payments, or navigate to the Ledger.

 Add Deposit

Users enter a description, vendor, and amount. The application automatically records the date and time and saves the deposit as a positive amount.

 Make Payment

Users enter payment details, including description, vendor, and amount. Payments are saved as negative amounts.

 Ledger

Displays all transactions, deposits only, or payments only, with the latest entries shown first.

Reports

Allows users to view transactions by month-to-date, previous month, year-to-date, or previous year.

 Custom Search

Allows users to search transactions by start date, end date, description, vendor, or amount.

 Help Menu

Displays instructions and available options to help users navigate the application.

Transactions are saved in a CSV file and loaded into an ArrayList when the application starts, allowing users to access previously saved transactions.

##how to run the application
Download or clone the project from GitHub:'''git clone https://github.com/fbsrat/restaurantsale/tree/master/src/main/java.git'''

Open the project in IntelliJ IDEA or another Java IDE.

Make sure Java is installed 

Open src/main/java/Main.java.

Run the Main class to start the application.

The application will load saved transactions and display the Home Page
