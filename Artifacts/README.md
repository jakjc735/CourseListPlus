1) Clone and Run Android App

This README file explains how to clone a repository and run an Android app. The steps below assume that you have already installed Android Studio on your local machine.

Cloning the Repository

* Open the GitLab repository that contains the Android app that you want to run.
* Click on the "Clone" button to copy the repository URL.
* Open Android Studio and click on "File" > "New" > "Project from Version Control" > "Git".
* Paste the repository URL that you copied earlier into the "URL" field.
* Choose a directory on your local machine where you want to clone the repository.
* Click on "Clone".

2) Running the App

* Once the repository has been cloned, open the project in Android Studio.
* Wait for Android Studio to build the project and download any necessary dependencies.
* Connect an Android device to your local machine or open an emulator.
* Click on the "Run" button (the green triangle icon) in Android Studio to run the app.
Choose the device or emulator that you want to run the app on and click on "OK".

3) What Can a user do? 

* Upon downloading our app users will be able to search (from a subset) of classes to by CRN, course credit hours, professor names, meeting time, and meeting day by navigating to the course list screen (click the button in the top left corner of the screen) and typing specific commmands in the search bar. 

* Our search bar takes the specific keywords as listed above and does queries for available courses in our database. If the query result is found in the database our app will return an course to the courselist view. Otherwise, the screen will remain blank and wait for a successful query.      

Note: For the time being users are only able to search for 5 classes as shown in the link below by the elements under the different column names**


Link To database screenshot to see attributes to search and get back queries: https://drive.google.com/file/d/1N2vF3AeGcoxOsHewBEFGMhvegg31UQze/view?usp=sharing


4) How did we established tests?

* Our (J-unit, Acceptance, and Integration) tests were designed around the search bar functionality and making sure that the queries returned the right courses depending on what type of search attribute users were trying to accomplish. Our test took into account null queries, invalid queries, and other user input that could potential crash the application. 

**4) Want to see how our app works?**

*View our video here: https://drive.google.com/file/d/1T-fUHm1ckLubAAJtbz0in5FBQQdxecfA/view?usp=sharing 






